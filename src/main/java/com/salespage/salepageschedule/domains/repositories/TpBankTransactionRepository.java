package com.salespage.salepageschedule.domains.repositories;

import com.salespage.salepageschedule.domains.entities.TpBankTransaction;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TpBankTransactionRepository extends MongoRepository<TpBankTransaction, ObjectId> {
  TpBankTransaction findByTransId(String id);

  TpBankTransaction findByDescriptionLike(String description);
}
