package com.salespage.salepageschedule.domains.services;

import com.salespage.salepageschedule.app.responses.Statistic.TotalPaymentStatisticResponse;
import com.salespage.salepageschedule.domains.Constants;
import com.salespage.salepageschedule.domains.entities.ProductStatistic;
import com.salespage.salepageschedule.domains.entities.Product;
import com.salespage.salepageschedule.domains.entities.StatisticCheckpoint;
import com.salespage.salepageschedule.domains.utils.DateUtils;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

@Service
public class PaymentStatisticService extends BaseService {
  public void asyncStatisticPreDay() {
    List<Product> products = productStorage.findAll();
    StatisticCheckpoint statisticCheckpoint = statisticCheckpointStorage.findById(Constants.PAYMENT_STATISTIC_CHECKPOINT);
    if (Objects.isNull(statisticCheckpoint)) {
      statisticCheckpoint = new StatisticCheckpoint();
      statisticCheckpoint.setCheckPoint(DateUtils.now().toLocalDate().minusDays(64));
      statisticCheckpoint.setId(Constants.PAYMENT_STATISTIC_CHECKPOINT);
    }
    for (LocalDate current = statisticCheckpoint.getCheckPoint(); current.isBefore(DateUtils.now().toLocalDate()); current = current.plusDays(1)) {
      for (Product product : products) {
        ProductStatistic paymentStatistic = productStatisticStorage.findByDailyAndProductId(current, product.getId().toHexString());
        if (paymentStatistic == null) {
          paymentStatistic = new ProductStatistic();
          paymentStatistic.setDaily(current);
          paymentStatistic.setProductId(product.getId().toHexString());
          paymentStatistic.setProductName(product.getProductName());
        }
        TotalPaymentStatisticResponse totalPaymentStatisticResponse = lookupAggregation(product.getId().toHexString(), current, current.plusDays(1));
        paymentStatistic.partnerFromStatistic(totalPaymentStatisticResponse);

        productStatisticStorage.save(paymentStatistic);
      }
      statisticCheckpoint.setCheckPoint(current.plusDays(1));
      statisticCheckpointStorage.save(statisticCheckpoint);
    }
  }

  public void asyncStatisticToday() {
    LocalDate startDay = DateUtils.startOfDay().toLocalDate();
    LocalDate endDay = startDay.plusDays(1);
    List<Product> products = productStorage.findAll();

    for (Product product : products) {
      ProductStatistic paymentStatistic = productStatisticStorage.findByDailyAndProductId(startDay, product.getId().toHexString());
      if (paymentStatistic == null) {
        paymentStatistic = new ProductStatistic();
        paymentStatistic.setDaily(startDay);
        paymentStatistic.setProductId(product.getId().toHexString());
        paymentStatistic.setProductName(product.getProductName());
      }
      TotalPaymentStatisticResponse totalPaymentStatisticResponse = lookupAggregation(product.getId().toHexString(), startDay, endDay);
      paymentStatistic.partnerFromStatistic(totalPaymentStatisticResponse);
      productStatisticStorage.save(paymentStatistic);
    }

  }

  public TotalPaymentStatisticResponse lookupAggregation(String productId, LocalDate gte, LocalDate lte) {
    Criteria criteria = Criteria.where("product_id").is(productId)
        .andOperator(Criteria.where("created_at").gte(DateUtils.convertLocalDateToLong(gte)), Criteria.where("created_at").lte(DateUtils.convertLocalDateToLong(lte)));
    AggregationOperation match = Aggregation.match(criteria);
    GroupOperation groupOperation = Aggregation.group()
        .sum("total_price").as("totalPurchase")
        .sum("ship_cod").as("totalShipCod");

    Aggregation aggregation = newAggregation(match, groupOperation);
    AggregationResults<TotalPaymentStatisticResponse> result
        = mongoTemplate.aggregate(aggregation, "product_transaction", TotalPaymentStatisticResponse.class);
    TotalPaymentStatisticResponse response = new TotalPaymentStatisticResponse();
    if (result.getUniqueMappedResult() != null) {
      response = result.getUniqueMappedResult();
    }
    return response;
  }

  public void updateToHotProduct(){
    List<ProductStatistic> productStatistics = productStatisticStorage.findTop10ByOrderByTotalViewDesc();
    List<Product> newHotProduct =  productStorage.findByIdIn(productStatistics.stream().map(ProductStatistic::getProductId).collect(Collectors.toList()));
    newHotProduct.forEach(k -> k.setIsHot(true));
    productStorage.saveAll(newHotProduct);
  }

  public void updateToNormalProduct(){
    List<Product> products = productStorage.findAll();
    products.forEach(k->k.setIsHot(false));
    List<ProductStatistic> productStatistics = productStatisticStorage.findTop10ByOrderByTotalViewDesc();
    Map<String, ProductStatistic>  map = productStatistics.stream().collect(Collectors.toMap(ProductStatistic::getProductId, Function.identity()));
    products.stream().filter(k -> map.get(k.getId().toHexString()) != null).forEach(k->k.setIsHot(true));
    productStorage.saveAll(products);
  }
}
