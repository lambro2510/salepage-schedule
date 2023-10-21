package com.salespage.salepageschedule.domains.storages;

import com.salespage.salepageschedule.domains.entities.Product;
import com.salespage.salepageschedule.domains.entities.ProductStatistic;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ProductStatisticStorage extends BaseStorage {
  public void save(ProductStatistic paymentStatistic) {
    productStatisticRepository.save(paymentStatistic);
  }

  public void update(ProductStatistic paymentStatistic) {
    productStatisticRepository.save(paymentStatistic);
  }
  public List<ProductStatistic> findByDailyBetween(LocalDate startDate, LocalDate endDate) {
    return productStatisticRepository.findByDailyBetween(startDate, endDate);
  }

  public ProductStatistic findByDailyAndProductId(LocalDate daily, String productId) {
    return productStatisticRepository.findByDailyAndProductId(daily, productId);
  }

  public List<ProductStatistic> findByProductIdAndDailyBetween(String productId, LocalDate startDate, LocalDate endDate) {
    return productStatisticRepository.findByProductIdAndDailyBetween(productId, startDate, endDate);

  }

  public List<ProductStatistic> findTop100ByOrderByTotalViewDesc() {
    return productStatisticRepository.findTop100ByOrderByTotalViewDesc();
  }

  public List<ProductStatistic> findTop100ByOrderByTotalView() {
    return productStatisticRepository.findTop100ByOrderByTotalView();
  }
}
