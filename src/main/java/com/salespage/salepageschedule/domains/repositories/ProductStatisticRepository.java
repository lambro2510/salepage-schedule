package com.salespage.salepageschedule.domains.repositories;

import com.salespage.salepageschedule.domains.entities.ProductStatistic;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductStatisticRepository extends MongoRepository<ProductStatistic, ObjectId> {

  List<ProductStatistic> findByDailyBetween(LocalDate startDate, LocalDate endDate);

  ProductStatistic findByDailyAndProductId(LocalDate daily, String productId);

  List<ProductStatistic> findByProductIdAndDailyBetween(String productId, LocalDate startDate, LocalDate endDate);

  List<ProductStatistic> findTop100ByOrderByTotalViewDesc();
}
