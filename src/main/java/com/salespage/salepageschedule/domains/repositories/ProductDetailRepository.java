package com.salespage.salepageschedule.domains.repositories;

import com.salespage.salepageschedule.domains.entities.ProductDetail;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailRepository extends MongoRepository<ProductDetail, ObjectId> {
  List<ProductDetail> findByProductId(String productId);

  List<ProductDetail> findByIdIn(List<ObjectId> ids);
}
