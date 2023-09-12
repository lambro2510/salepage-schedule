package com.salespage.salepageschedule.app.dtos.bankDtos;


import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BankAccountInfoRequest {

  @NotBlank(message = "BIN là bắt buộc")
  private String bin;

  @NotBlank(message = "Số tài khoản là bắt buộc")
  private String accountNumber;
}
