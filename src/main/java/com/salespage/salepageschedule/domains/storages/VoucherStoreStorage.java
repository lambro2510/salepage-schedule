package com.salespage.salepageschedule.domains.storages;

import com.salespage.salepageschedule.domains.entities.VoucherStore;
import com.salespage.salepageschedule.domains.entities.types.VoucherStoreType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VoucherStoreStorage extends BaseStorage {
  public void save(VoucherStore voucherStore) {
    voucherStoreRepository.save(voucherStore);
  }

  public VoucherStore findVoucherStoreById(String voucherStoreId) {
    return voucherStoreRepository.findVoucherStoreById(voucherStoreId);
  }

  public void deleteVoucherStoreById(String voucherStoreId) {
    voucherStoreRepository.deleteVoucherStoreById(voucherStoreId);
  }

  public List<VoucherStore> findVoucherStoreByCreatedBy(String username) {
    return voucherStoreRepository.findVoucherStoreByCreatedBy(username);
  }

  public List<VoucherStore> findByVoucherStoreTypeAndRefId(VoucherStoreType voucherStoreType, String refId) {
    return voucherStoreRepository.findByVoucherStoreTypeAndRefId(voucherStoreType, refId);
  }

  public Page<VoucherStore> findVoucherStoreByCreatedBy(String username, Pageable pageable) {
    return voucherStoreRepository.findVoucherStoreByCreatedBy(username, pageable);

  }
}