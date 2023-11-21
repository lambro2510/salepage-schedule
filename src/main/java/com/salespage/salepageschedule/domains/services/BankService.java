package com.salespage.salepageschedule.domains.services;

import com.salespage.salepageschedule.app.dtos.bankDtos.BankAccountInfoRequest;
import com.salespage.salepageschedule.app.responses.BankResponse.BankAccountData;
import com.salespage.salepageschedule.app.responses.BankResponse.BankListData;
import com.salespage.salepageschedule.app.responses.BankResponse.MbBankTransaction;
import com.salespage.salepageschedule.app.responses.BankResponse.VietQrResponse;
import com.salespage.salepageschedule.domains.entities.BankAccount;
import com.salespage.salepageschedule.domains.entities.BankTransaction;
import com.salespage.salepageschedule.domains.entities.PaymentTransaction;
import com.salespage.salepageschedule.domains.entities.status.BankStatus;
import com.salespage.salepageschedule.domains.entities.status.PaymentStatus;
import com.salespage.salepageschedule.domains.entities.types.NotificationMessage;
import com.salespage.salepageschedule.domains.entities.types.NotificationType;
import com.salespage.salepageschedule.domains.exceptions.ResourceExitsException;
import com.salespage.salepageschedule.domains.exceptions.ResourceNotFoundException;
import com.salespage.salepageschedule.domains.producer.Producer;
import com.salespage.salepageschedule.domains.utils.JsonParser;
import com.salespage.salepageschedule.domains.utils.RequestUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Log4j2
public class BankService extends BaseService {
  @Autowired
  Producer producer;
  @Autowired
  NotificationService notificationService;
  @Autowired
  PaymentService paymentService;
  @Value("${casso.bank-acc-id}")
  private String BANKACCID;
  @Value("${casso.apikey}")
  private String APIKEY;
  @Value("${casso.url}")
  private String URL;
  @Value("${vietqr.api.client-id}")
  private String VIETQRCLIENTID;
  @Value("${vietqr.api.apikey}")
  private String VIETQRAPIKEY;
  @Value("${vietqr.api.url}")
  private String VIETQRURL;

  @Value("${mb-bank.url}")
  private String MB_URL;

  @Value("${mb-bank.password}")
  private String MB_PASSWORD;

  @Value("${mb-bank.account-no}")
  private String MB_ACCOUNT_NO;

  @Value("${mb-bank.token}")
  private String MB_BANK_TOKEN;

  public List<BankTransaction> getAllTransaction() {
    return bankTransactionStorage.findAll();
  }

  public List<BankListData> getListBank() throws IOException {
    VietQrResponse response = RequestUtil.request(HttpMethod.GET, VIETQRURL + "/v2/banks", VietQrResponse.class, null, new HashMap<>());
    if (Objects.isNull(response))
      throw new ResourceNotFoundException("Lỗi hệ thống, không lấy được danh sách ngân hàng");
    if (!Objects.equals(response.getCode(), "00")) throw new ResourceNotFoundException(response.getDesc());
    log.info(response);
    return JsonParser.arrayList(JsonParser.toJson(response.getData()), BankListData.class);
  }

  public BankAccountData getBankAccountData(String bin, String accountNo) throws IOException {
    Map<String, String> header = new HashMap<>();
    header.put("x-client-id", VIETQRCLIENTID);
    header.put("x-api-key", VIETQRAPIKEY);
    BankAccountInfoRequest request = new BankAccountInfoRequest();
    request.setBin(bin);
    request.setAccountNumber(accountNo);
    VietQrResponse response = RequestUtil.request(HttpMethod.POST, VIETQRURL + "/v2/lookup", VietQrResponse.class, request, header);
    if (Objects.isNull(response))
      throw new ResourceNotFoundException("Lỗi hệ thống, không lấy được thông tin tài khoản ngân hàng");
    if (!Objects.equals(response.getCode(), "00")) throw new ResourceNotFoundException(response.getDesc());
    log.info("----getBankAccountData: " + response);
    return JsonParser.entity(JsonParser.toJson(response.getData()), BankAccountData.class);
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE, noRollbackFor = {ResourceNotFoundException.class})
  @Retryable(maxAttempts = 5, backoff = @Backoff(delay = 1000))
  public void checkNotResolveTransaction() throws Exception {
    List<PaymentTransaction> paymentTransactions = paymentTransactionStorage.findByPaymentStatus(PaymentStatus.WAITING);
    log.info("----checkNotResolveTransaction----: " + paymentTransactions.size() + " paymentTransactions chua duoc xu ly");
    log.info("paymentTransaction: {{}}", paymentTransactions);
    for (PaymentTransaction paymentTransaction : paymentTransactions) {
      if (paymentTransaction.createdOneDayPeriod()) {
        paymentTransaction.setPaymentStatus(PaymentStatus.PENDING);
        paymentTransaction.setDescription("Giao dịch không được xử lý");
        notificationService.createNotification(paymentTransaction.getUsername(), NotificationMessage.CHANGE_STATUS_PAYMENT_PENDING.getTittle(), NotificationMessage.CHANGE_STATUS_PAYMENT_PENDING.getMessage(), NotificationType.PAYMENT_TRANSACTION, paymentTransaction.getId().toHexString());
        paymentTransactionStorage.save(paymentTransaction);
      }
      paymentService.confirmPayment(paymentTransaction.getUsername(), paymentTransaction.getId().toHexString());
    }
  }

  public void linkBankAccount(String username, BankAccountInfoRequest request) throws Exception {
    List<BankListData> bankListData = getListBank();
    Map<String, BankListData> bankMap = bankListData.stream()
        .collect(Collectors.toMap(BankListData::getBin, Function.identity()));

    BankListData bankData = bankMap.get(request.getBin());
    if (Objects.isNull(bankData)) throw new ResourceNotFoundException("Ngân hàng không được hỗ trợ");

    BankAccount bankAccount = bankAccountStorage.findByBankIdAndAccountNo(bankData.getId(), request.getAccountNumber());
    if (Objects.nonNull(bankAccount)) throw new ResourceExitsException("Tài khoản đã được liên kết");
    else bankAccount = new BankAccount();

    BankAccountData bankAccountData = getBankAccountData(request.getBin(), request.getAccountNumber());
    if (Objects.isNull(bankAccountData)) throw new Exception("Tài khoản ngân hàng không hợp lệ");

    bankAccount.setBin(request.getBin());
    bankAccount.setAccountNo(request.getAccountNumber());
    bankAccount.setBankName(bankData.getShortName());
    bankAccount.setBankFullName(bankData.getName());
    bankAccount.setUsername(username);
    bankAccount.setBankId(bankData.getId());
    bankAccount.setBankLogoUrl(bankData.getLogo());
    bankAccount.setBankAccountName(bankAccountData.getAccountName());
    bankAccount.setStatus(BankStatus.ACTIVE);
    bankAccount.setMoneyIn(0D);
    bankAccount.setMoneyOut(0D);
    bankAccountStorage.save(bankAccount);
  }

  public List<MbBankTransaction.Transaction> getMbBankTransaction() throws IOException {
    String baseUrl = MB_URL + "/" + MB_PASSWORD + "/" + MB_ACCOUNT_NO + "/" + MB_BANK_TOKEN;
    MbBankTransaction mbBankTransaction = JsonParser.entity(RequestUtil.request(HttpMethod.GET, baseUrl, String.class, null, null), MbBankTransaction.class);
    return mbBankTransaction.getData();
  }

  public void saveBankTransaction() throws IOException {
    List<MbBankTransaction.Transaction> transactions = getMbBankTransaction();
    for (MbBankTransaction.Transaction transaction : transactions) {
      BankTransaction bankTransaction = bankTransactionStorage.findByRefNo(transaction.getRefNo());
      if (Objects.isNull(bankTransaction)) {
        bankTransaction = new BankTransaction();
        bankTransaction.partnerFromTransactionData(transaction);
        bankTransactionStorage.save(bankTransaction);
      }
    }
  }
}