package com.salespage.salepageschedule.domains.repositories;

import com.salespage.salepageschedule.domains.entities.TransactionStatistic;
import com.salespage.salepageschedule.domains.entities.types.StatisticType;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionStatisticRepository extends MongoRepository<TransactionStatistic, ObjectId> {
  TransactionStatistic findByDate(String date);

  TransactionStatistic findByDateAndProductId(String date, String productId);

  TransactionStatistic findByDateAndProductIdAndStatisticType(String date, String productId, StatisticType statisticType);
}
