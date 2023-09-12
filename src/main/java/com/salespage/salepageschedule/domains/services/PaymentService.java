package com.salespage.salepageschedule.domains.services;

import com.salespage.salepageschedule.app.responses.InfoResponse;
import com.salespage.salepageschedule.domains.entities.*;
import com.salespage.salepageschedule.domains.entities.status.BankStatus;
import com.salespage.salepageschedule.domains.entities.status.PaymentStatus;
import com.salespage.salepageschedule.domains.entities.types.NotificationMessage;
import com.salespage.salepageschedule.domains.entities.types.NotificationType;
import com.salespage.salepageschedule.domains.exceptions.BadRequestException;
import com.salespage.salepageschedule.domains.exceptions.ResourceNotFoundException;
import com.salespage.salepageschedule.domains.producer.Producer;
import com.salespage.salepageschedule.domains.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class PaymentService extends BaseService {

  @Autowired
  NotificationService notificationService;

  @Autowired
  Producer producer;

  @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED, noRollbackFor = {ResourceNotFoundException.class})
  @Retryable(maxAttempts = 5, backoff = @Backoff(delay = 1000))
  public InfoResponse confirmPayment(String username, String paymentId) {
    InfoResponse info;
    try {
      User user = userStorage.findByUsername(username);
      if (Objects.isNull(user)) throw new ResourceNotFoundException("Không tồn tại người dùng này");
      PaymentTransaction paymentTransaction = paymentTransactionStorage.findByIdAndUsernameAndPaymentStatus(paymentId, username, PaymentStatus.WAITING);
      if (Objects.isNull(paymentTransaction))
        throw new ResourceNotFoundException("Giao dịch không tồn tại hoặc đã được thanh toán");
      else {
        BankAccount bankAccount = bankAccountStorage.findBankAccountById(paymentTransaction.getBankAccountId());
        if (Objects.isNull(bankAccount))
          throw new ResourceNotFoundException("Tài khoản ngân hàng liên kết không tồn tại");
        if (!bankAccount.getStatus().equals(BankStatus.ACTIVE))
          throw new BadRequestException("Liên kết ngân hàng này đã chưa được kích hoạt");
        info = findPaymentByTpBank(paymentTransaction, user, bankAccount);
        if (info.getCode() == 1) {
          info = findPaymentByMbBank(paymentTransaction, user, bankAccount);
        }
        paymentTransaction.setDescription(info.getMessage());
        userStorage.save(user);
        paymentTransactionStorage.save(paymentTransaction);
        bankAccountStorage.save(bankAccount);
      }
    } catch (ResourceNotFoundException ex) {
      return new InfoResponse(1, ex.getMessage());
    }

    return info;
  }

  public InfoResponse findPaymentByTpBank(PaymentTransaction paymentTransaction, User user, BankAccount bankAccount) {
    TpBankTransaction tpBankTransaction = tpBankTransactionStorage.findByDescription(Helper.genDescription(paymentTransaction.getId().toHexString()));
    if (Objects.isNull(tpBankTransaction)) return new InfoResponse(1, "Giao dịch đang được xử lý");
    Integer amount = Integer.parseInt(tpBankTransaction.getAmount());
    String message = null;
    if (Objects.equals(tpBankTransaction.getCreditDebitIndicator(), "CRDT")) {
      paymentTransaction.setPaymentStatus(PaymentStatus.RESOLVE);
      user.getBalance().addMoney(amount);
      bankAccount.setMoneyIn(bankAccount.getMoneyIn() + amount);
      message = "Tài khoản của bạn được cộng " + amount;
      notificationService.createNotification(user.getUsername(), NotificationMessage.CHANGE_STATUS_PAYMENT_RESOLVE_IN.getTittle(), NotificationMessage.CHANGE_STATUS_PAYMENT_RESOLVE_IN.getMessage(), NotificationType.PAYMENT_TRANSACTION, paymentTransaction.getId().toHexString());
    } else {
      if (user.getBalance().getMoney() > amount) {
        paymentTransaction.setPaymentStatus(PaymentStatus.RESOLVE);
        user.getBalance().minusMoney(amount);
        bankAccount.setMoneyOut(bankAccount.getMoneyOut() + amount);
        message = "Tài khoản của bạn bị trừ " + amount;
        notificationService.createNotification(user.getUsername(), NotificationMessage.CHANGE_STATUS_PAYMENT_RESOLVE_OUT.getTittle(), NotificationMessage.CHANGE_STATUS_PAYMENT_RESOLVE_OUT.getMessage(), NotificationType.PAYMENT_TRANSACTION, paymentTransaction.getId().toHexString());
      } else {
        paymentTransaction.setPaymentStatus(PaymentStatus.CANCEL);
        paymentTransaction.setDescription("Giao dịch đã bị hủy bỏ do tài khoản của bạn không đủ tiền");
        notificationService.createNotification(user.getUsername(), NotificationMessage.CHANGE_STATUS_PAYMENT_RESOLVE_OUT_ERR.getTittle(), NotificationMessage.CHANGE_STATUS_PAYMENT_RESOLVE_OUT_ERR.getMessage(), NotificationType.PAYMENT_TRANSACTION, paymentTransaction.getId().toHexString());
      }
    }
    paymentTransaction.setDescription(message);
    return new InfoResponse(0, "Xử lý giao dịch thành công");
  }

  public InfoResponse findPaymentByMbBank(PaymentTransaction paymentTransaction, User user, BankAccount bankAccount) {
    BankTransaction bankTransaction = bankTransactionStorage.findByDescription(paymentTransaction.getId().toHexString());
    String message;
    if (Objects.isNull(bankTransaction)) return new InfoResponse(1, "Giao dịch đang được xử lý");
    if (bankTransaction.getDebitAmount() > 0) {
      if (user.getBalance().getMoney() < bankTransaction.getDebitAmount()) {
        paymentTransaction.setPaymentStatus(PaymentStatus.CANCEL);
        paymentTransaction.setDescription("Giao dịch đã bị hủy bỏ do tài khoản của bạn không đủ tiền");
        notificationService.createNotification(user.getUsername(), NotificationMessage.CHANGE_STATUS_PAYMENT_RESOLVE_OUT_ERR.getTittle(), NotificationMessage.CHANGE_STATUS_PAYMENT_RESOLVE_OUT_ERR.getMessage(), NotificationType.PAYMENT_TRANSACTION, paymentTransaction.getId().toHexString());
        return new InfoResponse(1, "Giao dịch đã bị hủy bỏ do tài khoản của bạn không đủ tiền");
      } else {
        paymentTransaction.setPaymentStatus(PaymentStatus.RESOLVE);
        user.getBalance().minusMoney(bankTransaction.getDebitAmount().longValue());
        bankAccount.setMoneyOut(bankAccount.getMoneyOut() + bankTransaction.getDebitAmount().longValue());
        message = "Tài khoản của bạn bị trừ " + bankTransaction.getDebitAmount().longValue();
        notificationService.createNotification(user.getUsername(), NotificationMessage.CHANGE_STATUS_PAYMENT_RESOLVE_OUT.getTittle(), NotificationMessage.CHANGE_STATUS_PAYMENT_RESOLVE_OUT.getMessage(), NotificationType.PAYMENT_TRANSACTION, paymentTransaction.getId().toHexString());
      }
    }
    if (bankTransaction.getCreditAmount() > 0) {
      paymentTransaction.setPaymentStatus(PaymentStatus.RESOLVE);
      user.getBalance().addMoney(bankTransaction.getCreditAmount().longValue());
      bankAccount.setMoneyIn(bankAccount.getMoneyIn() + bankTransaction.getCreditAmount().longValue());
      message = "Tài khoản của bạn được cộng " + bankTransaction.getCreditAmount().longValue();
      notificationService.createNotification(user.getUsername(), NotificationMessage.CHANGE_STATUS_PAYMENT_RESOLVE_IN.getTittle(), NotificationMessage.CHANGE_STATUS_PAYMENT_RESOLVE_IN.getMessage(), NotificationType.PAYMENT_TRANSACTION, paymentTransaction.getId().toHexString());
    }
    return new InfoResponse(0, "Xử lý giao dịch thành công");
  }

}
