package com.salespage.salepageschedule.domains.repositories;

import com.salespage.salepageschedule.domains.entities.ProductType;
import com.salespage.salepageschedule.domains.entities.status.ProductTypeStatus;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTypeRepository extends MongoRepository<ProductType, ObjectId> {
  ProductType findByProductType(String productType);

  List<ProductType> findByStatus(ProductTypeStatus status);

  List<ProductType> findTop20ByProductTypeNameLikeOrProductTypeLikeAndStatus(String typeName, String type, ProductTypeStatus status);

  List<ProductType> findTop20By();

  List<ProductType> findTop20ByProductTypeNameLike(String typeName);
}
