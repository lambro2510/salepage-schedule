package com.salespage.salepageschedule.domains.repositories;

import com.salespage.salepageschedule.domains.entities.PaymentStatistic;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PaymentStatisticRepository extends MongoRepository<PaymentStatistic, ObjectId> {

  List<PaymentStatistic> findByDailyBetween(LocalDate startDate, LocalDate endDate);

  PaymentStatistic findByDailyAndProductId(LocalDate daily, String productId);

  List<PaymentStatistic> findByProductIdAndDailyBetween(String productId, LocalDate startDate, LocalDate endDate);
}
