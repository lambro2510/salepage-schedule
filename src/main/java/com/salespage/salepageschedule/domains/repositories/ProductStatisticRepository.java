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

  ProductStatistic findByDailyAndProductDetailId(LocalDate daily, String productId);

  List<ProductStatistic> findByProductDetailIdAndDailyBetween(String productId, LocalDate startDate, LocalDate endDate);

  List<ProductStatistic> findTop100ByOrderByTotalViewDesc();

  List<ProductStatistic> findTop100ByOrderByTotalView();

  List<ProductStatistic> findDistinctTop100ByOrderByTotalView();
}
