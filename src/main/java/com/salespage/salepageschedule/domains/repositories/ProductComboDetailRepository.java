package com.salespage.salepageschedule.domains.repositories;

import com.salespage.salepageschedule.domains.entities.ProductComboDetail;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductComboDetailRepository extends MongoRepository<ProductComboDetail, ObjectId> {
  List<ProductComboDetail> findByProductIdIn(List<String> ids);
}