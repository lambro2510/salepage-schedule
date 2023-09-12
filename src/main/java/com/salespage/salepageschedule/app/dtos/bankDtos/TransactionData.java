package com.salespage.salepageschedule.app.dtos.bankDtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class TransactionData {

  @NotNull(message = "Ngày đăng ký là bắt buộc")
  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  private LocalDateTime postingDate;

  @NotNull(message = "Ngày giao dịch là bắt buộc")
  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  private LocalDateTime transactionDate;

  @NotNull(message = "Số tài khoản là bắt buộc")
  private String accountNo;

  private Double creditAmount;
  private Double debitAmount;

  private String currency;

  private String description;

  private Double availableBalance;

  private String beneficiaryAccount;

  private String refNo;

  private String benAccountName;

  private String bankName;

  private String benAccountNo;
}
