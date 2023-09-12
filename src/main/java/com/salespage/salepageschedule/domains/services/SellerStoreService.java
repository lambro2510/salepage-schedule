package com.salespage.salepageschedule.domains.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class SellerStoreService extends BaseService {

  @Autowired
  @Lazy
  private MapService mapService;


}
