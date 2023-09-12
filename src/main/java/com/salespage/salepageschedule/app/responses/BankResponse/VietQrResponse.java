package com.salespage.salepageschedule.app.responses.BankResponse;

import lombok.Data;

@Data
public class VietQrResponse<T> {
  private String code;

  private String desc;

  private T data;
}
