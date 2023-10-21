package com.salespage.salepageschedule.domains.repositories;

import com.salespage.salepageschedule.domains.entities.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
  User findByUsername(String username);

  User findUserById(ObjectId userId);

  List<User> findByIdIn(List<ObjectId> objectIds);
}
