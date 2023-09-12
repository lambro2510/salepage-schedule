package com.salespage.salepageschedule.domains.exceptions;

import com.salespage.salepageschedule.domains.exceptions.info.BaseException;
import com.salespage.salepageschedule.domains.exceptions.info.ErrorCode;

public class AuthorizationException extends BaseException {

  public AuthorizationException(String message) {
    super(ErrorCode.AUTHORIZATION, message);
  }

  public AuthorizationException() {
    super(ErrorCode.AUTHORIZATION, "Bạn không có quyền để thực hiện chức năng này, vui lòng kiểm tra lại");
  }
}
