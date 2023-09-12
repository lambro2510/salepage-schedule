package com.salespage.salepageschedule.domains.repositories;

import com.salespage.salepageschedule.domains.entities.StatisticCheckpoint;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticCheckpointRepository extends MongoRepository<StatisticCheckpoint, String> {
  StatisticCheckpoint findStatisticCheckpointById(String id);
}
