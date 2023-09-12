package com.salespage.salepageschedule.domains.exceptions;


import com.salespage.salepageschedule.domains.exceptions.info.BaseException;
import com.salespage.salepageschedule.domains.exceptions.info.ErrorCode;

public class UnauthorizedException extends BaseException {
  public UnauthorizedException() {
    super(ErrorCode.UNAUTHORIZED, "Account not found");
  }
}
