package com.salespage.salepageschedule.app.responses.BankResponse;

import com.salespage.salepageschedule.domains.entities.BankAccount;
import com.salespage.salepageschedule.domains.entities.status.BankStatus;
import lombok.Data;

@Data
public class BankAccountResponse {

  private String bankAccountId;

  private String username;

  private String accountNo;

  private Long bankId;

  private String bin;

  private String bankName;

  private String bankLogoUrl;

  private String bankFullName;

  private String bankAccountName;

  private BankStatus status;

  private Double moneyIn;

  private Double moneyOut;

  public void assignFromBankAccount(BankAccount bankAccount) {
    bankAccountId = bankAccount.getId().toHexString();
    username = bankAccount.getUsername();
    accountNo = bankAccount.getAccountNo();
    bankId = bankAccount.getBankId();
    bin = bankAccount.getBin();
    bankName = bankAccount.getBankName();
    bankLogoUrl = bankAccount.getBankLogoUrl();
    bankFullName = bankAccount.getBankFullName();
    bankAccountName = bankAccount.getBankAccountName();
    status = bankAccount.getStatus();
    moneyIn = bankAccount.getMoneyIn();
    moneyOut = bankAccount.getMoneyOut();
  }
}