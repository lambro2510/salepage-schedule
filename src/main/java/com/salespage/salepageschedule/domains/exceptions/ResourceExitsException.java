package com.salespage.salepageschedule.domains.exceptions;

import com.salespage.salepageschedule.domains.exceptions.info.BaseException;
import com.salespage.salepageschedule.domains.exceptions.info.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)
public class ResourceExitsException extends BaseException {
  public ResourceExitsException() {
    super(ErrorCode.RESOURCE_FOUND, "Resource Found");
  }

  public ResourceExitsException(String message) {
    super(message);
  }
}
