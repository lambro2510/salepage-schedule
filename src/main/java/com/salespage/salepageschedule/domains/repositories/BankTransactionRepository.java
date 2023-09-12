package com.salespage.salepageschedule.domains.repositories;

import com.salespage.salepageschedule.domains.entities.BankTransaction;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankTransactionRepository extends MongoRepository<BankTransaction, ObjectId> {
  BankTransaction findByDescriptionLike(String description);

  BankTransaction findByRefNo(String refNo);
}
