package com.salespage.salepageschedule.domains.repositories;

import com.salespage.salepageschedule.domains.entities.VoucherCodeLimit;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherCodeLimitRepository extends MongoRepository<VoucherCodeLimit, ObjectId> {
  VoucherCodeLimit findByUsernameAndVoucherStoreId(String username, String voucherStoreId);
}