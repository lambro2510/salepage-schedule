package com.salespage.salepageschedule.domains.exceptions;


import com.salespage.salepageschedule.domains.exceptions.info.BaseException;
import com.salespage.salepageschedule.domains.exceptions.info.ErrorCode;

public class ResourceNotFoundException extends BaseException {

  public ResourceNotFoundException() {
    super(ErrorCode.RESOURCE_NOT_FOUND, "Resource Not Found");
  }

  public ResourceNotFoundException(String message) {
    super(ErrorCode.RESOURCE_NOT_FOUND, message);
  }
}
