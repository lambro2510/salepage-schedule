package com.salespage.salepageschedule.domains.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class VoucherCodeService extends BaseService {

  @Autowired
  @Lazy
  private VoucherStoreService voucherStoreService;


  public void deleteAllVoucherCodeInStore() {

  }

}
