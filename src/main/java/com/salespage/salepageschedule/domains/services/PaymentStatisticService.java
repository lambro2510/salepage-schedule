package com.salespage.salepageschedule.domains.services;

import com.salespage.salepageschedule.app.responses.Statistic.TotalPaymentStatisticResponse;
import com.salespage.salepageschedule.domains.Constants;
import com.salespage.salepageschedule.domains.entities.PaymentStatistic;
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
import java.util.Objects;

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
        PaymentStatistic paymentStatistic = paymentStatisticStorage.findByDailyAndProductId(current, product.getId().toHexString());
        if (paymentStatistic == null) {
          paymentStatistic = new PaymentStatistic();
          paymentStatistic.setDaily(current);
          paymentStatistic.setProductId(product.getId().toHexString());
          paymentStatistic.setProductName(product.getProductName());
          paymentStatistic.create();
        }
        TotalPaymentStatisticResponse totalPaymentStatisticResponse = lookupAggregation(product.getId().toHexString(), current, current.plusDays(1));
        paymentStatistic.partnerFromStatistic(totalPaymentStatisticResponse);

        paymentStatisticStorage.save(paymentStatistic);
      }
      statisticCheckpoint.setCheckPoint(current);
      statisticCheckpointStorage.save(statisticCheckpoint);
    }
  }

  public void asyncStatisticToday() {
    LocalDate startDay = DateUtils.startOfDay().toLocalDate();
    LocalDate endDay = startDay.plusDays(1);
    List<Product> products = productStorage.findAll();

    for (Product product : products) {
      PaymentStatistic paymentStatistic = paymentStatisticStorage.findByDailyAndProductId(startDay, product.getId().toHexString());
      if (paymentStatistic == null) {
        paymentStatistic = new PaymentStatistic();
        paymentStatistic.setDaily(startDay);
        paymentStatistic.setProductId(product.getId().toHexString());
        paymentStatistic.setProductName(product.getProductName());
        paymentStatistic.create();
      }
      TotalPaymentStatisticResponse totalPaymentStatisticResponse = lookupAggregation(product.getId().toHexString(), startDay, endDay);
      paymentStatistic.partnerFromStatistic(totalPaymentStatisticResponse);
      paymentStatisticStorage.save(paymentStatistic);
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


  private void partnerToResponse(TotalPaymentStatisticResponse statistic, PaymentStatistic paymentStatistic) {
    statistic.setTotalBuy(statistic.getTotalBuy() + paymentStatistic.getTotalBuy());
    statistic.setTotalPurchase(statistic.getTotalPurchase() + paymentStatistic.getTotalPurchase());
    statistic.setTotalUser(statistic.getTotalUser() + paymentStatistic.getTotalUser());
    TotalPaymentStatisticResponse.Daily daily = new TotalPaymentStatisticResponse.Daily();
    daily.setDaily(paymentStatistic.getDaily());
    daily.setTotalBuy(paymentStatistic.getTotalBuy());
    daily.setTotalPurchase(paymentStatistic.getTotalPurchase());
    daily.setTotalUser(paymentStatistic.getTotalUser());
    statistic.getDailies().add(daily);
  }
}
