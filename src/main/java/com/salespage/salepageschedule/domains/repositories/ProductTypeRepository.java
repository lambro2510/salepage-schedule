package com.salespage.salepageschedule.domains.repositories;

import com.salespage.salepageschedule.domains.entities.ProductType;
import com.salespage.salepageschedule.domains.entities.status.ProductTypeStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTypeRepository extends MongoRepository<ProductType, String> {
  ProductType findByProductType(String productType);

  List<ProductType> findByStatus(ProductTypeStatus status);

}
