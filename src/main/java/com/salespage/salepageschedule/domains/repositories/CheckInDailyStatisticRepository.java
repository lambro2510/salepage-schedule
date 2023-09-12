package com.salespage.salepageschedule.domains.repositories;

import com.salespage.salepageschedule.domains.entities.CheckInDailyStatistic;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckInDailyStatisticRepository extends MongoRepository<CheckInDailyStatistic, ObjectId> {
  CheckInDailyStatistic findByUsernameAndMonth(String username, String month);
}
