package com.salespage.salepageschedule.domains.exceptions;

import com.salespage.salepageschedule.domains.exceptions.info.BaseException;

public class VoucherCodeException extends BaseException {
  public VoucherCodeException(int code, String message) {
    super(code, message);
  }
}
