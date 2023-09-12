package com.salespage.salepageschedule.app.dtos.bankDtos;


import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class GenQrCodeDto {

  @NotBlank(message = "Số tài khoản là bắt buộc")
  private String accountNo;

  @NotBlank(message = "Tên tài khoản là bắt buộc")
  private String accountName;

  @NotBlank(message = "ID acquirer là bắt buộc")
  private String acqId;

  @NotNull(message = "Số tiền là bắt buộc")
  @Min(value = 0, message = "Số tiền phải lớn hơn hoặc bằng 0")
  private Long amount;

  private String addInfo;

  private String format;

  private String template;
}
