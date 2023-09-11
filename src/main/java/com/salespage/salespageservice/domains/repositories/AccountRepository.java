package com.salespage.salespageservice.domains.repositories;

import com.salespage.salespageservice.domains.entities.Account;
import com.salespage.salespageservice.domains.entities.types.UserRole;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
  Account findByUsername(String username);

  boolean existsByUsername(String username);

  boolean existsByUsernameAndRole(String username, UserRole role);

}

