package com.salespage.salepageschedule.domains.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoucherStoreService extends BaseService {

  @Autowired
  private VoucherCodeService voucherCodeService;

}
