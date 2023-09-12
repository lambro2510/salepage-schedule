package com.salespage.salepageschedule.domains.exceptions;

import com.salespage.salepageschedule.domains.exceptions.info.BaseException;
import com.salespage.salepageschedule.domains.exceptions.info.ErrorCode;

public class TransactionException extends BaseException {
  public TransactionException(String message) {
    super(ErrorCode.BAD_REQUEST, message);
  }

  public TransactionException(int code, String message) {
    super(code, message);
  }
}
