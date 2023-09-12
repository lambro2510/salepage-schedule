package com.salespage.salepageschedule.app.dtos.bankDtos;


import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class BankDto {

  @NotNull(message = "Lỗi là bắt buộc")
  private Integer error;

  @NotNull(message = "Dữ liệu giao dịch là bắt buộc")
  @Valid
  private List<TransactionData> data;
}
