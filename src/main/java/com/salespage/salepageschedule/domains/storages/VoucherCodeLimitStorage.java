package com.salespage.salepageschedule.domains.storages;

import com.salespage.salepageschedule.domains.entities.VoucherCodeLimit;
import org.springframework.stereotype.Component;

@Component
public class VoucherCodeLimitStorage extends BaseStorage {
  public VoucherCodeLimit findByUsernameAndVoucherStoreId(String username, String voucherStoreId) {
    return voucherCodeLimitRepository.findByUsernameAndVoucherStoreId(username, voucherStoreId);
  }

  public void save(VoucherCodeLimit voucherCodeLimit) {
    voucherCodeLimitRepository.save(voucherCodeLimit);
  }
}
