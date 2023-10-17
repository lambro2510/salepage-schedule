package com.salespage.salepageschedule.domains.repositories;

import com.salespage.salepageschedule.domains.entities.ProductCombo;
import com.salespage.salepageschedule.domains.entities.types.ActiveState;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductComboRepository extends MongoRepository<ProductCombo, ObjectId> {
  List<ProductCombo> findByCreatedBy(String username);

  ProductCombo findByIdAndState(ObjectId objectId, ActiveState activeState);
}
