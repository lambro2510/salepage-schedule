package com.salespage.salepageschedule.domains.repositories;

import com.salespage.salepageschedule.domains.entities.ProductCategory;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepository extends MongoRepository<ProductCategory, ObjectId> {
  List<ProductCategory> findByCreatedBy(String username);

  ProductCategory findByCreatedByAndId(String username, ObjectId id);

  List<ProductCategory> findByCategoryName(String categoryName);
}
