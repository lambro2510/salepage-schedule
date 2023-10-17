package com.salespage.salepageschedule.domains.repositories;

import com.salespage.salepageschedule.domains.entities.Account;
import com.salespage.salepageschedule.domains.entities.types.UserRole;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account, ObjectId> {
  Account findByUsername(String username);

  boolean existsByUsername(String username);

  boolean existsByUsernameAndRole(String username, UserRole role);

}

