package com.salespage.salepageschedule.domains.repositories;

import com.salespage.salepageschedule.domains.entities.ProductTransactionDetail;
import com.salespage.salepageschedule.domains.entities.types.ProductTransactionState;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTransactionDetailRepository extends MongoRepository<ProductTransactionDetail, ObjectId> {
  List<ProductTransactionDetail> findByState(ProductTransactionState state);

  List<ProductTransactionDetail> findByCreatedAtBetween(Long startAt, Long endAt);
}
