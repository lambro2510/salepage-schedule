package com.salespage.salepageschedule.domains.repositories;

import com.salespage.salepageschedule.domains.entities.Cart;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends MongoRepository<Cart, ObjectId> {
  List<Cart> findByUsername(String username);

  Long countByUsername(String username);
}
