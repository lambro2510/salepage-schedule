package com.salespage.salepageschedule.domains.repositories;

import com.salespage.salepageschedule.domains.entities.Config;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigRepository extends MongoRepository<Config, ObjectId> {
  Config findByKey(String key);

  void deleteByKey(String key);
}
