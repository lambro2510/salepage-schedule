package com.salespage.salepageschedule.domains.repositories;

import com.salespage.salepageschedule.domains.entities.VoucherStore;
import com.salespage.salepageschedule.domains.entities.types.VoucherStoreType;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoucherStoreRepository extends MongoRepository<VoucherStore, ObjectId> {
  VoucherStore findVoucherStoreById(String voucherStoreId);

  void deleteVoucherStoreById(String voucherStoreId);

  List<VoucherStore> findVoucherStoreByCreatedBy(String username);

  List<VoucherStore> findByVoucherStoreTypeAndRefId(VoucherStoreType voucherStoreType, String productId);

  Page<VoucherStore> findVoucherStoreByCreatedBy(String username, Pageable pageable);
}
