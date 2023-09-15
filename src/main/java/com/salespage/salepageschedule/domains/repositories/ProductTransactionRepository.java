package com.salespage.salepageschedule.domains.repositories;

import com.salespage.salepageschedule.domains.entities.ProductTransaction;
import com.salespage.salepageschedule.domains.entities.types.ProductTransactionState;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTransactionRepository extends MongoRepository<ProductTransaction, ObjectId> {
  ProductTransaction findProductTransactionById(ObjectId id);

  Page<ProductTransaction> findAll(Query query, Pageable pageable);

  List<ProductTransaction> findAllProductTransactionByProductId(ObjectId productId);

  @org.springframework.data.mongodb.repository.Query(value = "")
  long countByProductId(ObjectId productId);

  List<ProductTransaction> findByCreatedAtBetween(Long startTimeOfDay, Long endTimeOfDay);

  Integer countByBuyerUsernameAndProductId(String username, String productId);

  List<ProductTransaction> findProductTransactionByState(ProductTransactionState productTransactionState);
}