package com.salespage.salepageschedule.domains.services;

import com.salespage.salepageschedule.app.responses.transactionResponse.TotalStatisticResponse;
import com.salespage.salepageschedule.domains.entities.ProductTransaction;
import com.salespage.salepageschedule.domains.entities.ProductTransactionDetail;
import com.salespage.salepageschedule.domains.entities.TransactionStatistic;
import com.salespage.salepageschedule.domains.entities.types.StatisticType;
import com.salespage.salepageschedule.domains.utils.Helper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class TransactionStatisticService extends BaseService {

  public void statisticToday() {
    LocalDate today = LocalDate.now();
    String date = today.getYear() + "-" + today.getMonthValue() + "-" + today.getDayOfMonth();
    statisticUpdate(date, Helper.getStartTimeOfDay(today), Helper.getEndTimeOfDay(today), StatisticType.DAY);
  }

  public void statisticPeriodDate() {
    LocalDate today = LocalDate.now();
    LocalDate periodDay = today.minusDays(1);
    String date = periodDay.getYear() + "-" + periodDay.getMonthValue() + "-" + periodDay.getDayOfMonth();
    statisticUpdate(date, Helper.getStartTimeOfDay(periodDay), Helper.getEndTimeOfDay(periodDay), StatisticType.DAY);
  }

  public void statisticWeek() {
    LocalDate today = LocalDate.now();
    String date = today.getYear() + "-" + today.getMonthValue() + "-" + today.getDayOfMonth();
    statisticUpdate(date, Helper.getStartTimeOfWeek(today), Helper.getEndTimeOfWeek(today), StatisticType.WEEK);
  }

  public void statisticPeriodWeek() {
    LocalDate today = LocalDate.now();
    LocalDate periodWeek = today.minusDays(7);
    String date = periodWeek.getYear() + "-" + periodWeek.getMonthValue() + "-" + periodWeek.getDayOfMonth();
    statisticUpdate(date, Helper.getStartTimeOfWeek(periodWeek), Helper.getEndTimeOfWeek(periodWeek), StatisticType.WEEK);
  }

  public void statisticMonth() {
    LocalDate today = LocalDate.now();
    String date = today.getYear() + "-" + today.getMonthValue();
    statisticUpdate(date, Helper.getStartTimeOfMonth(today), Helper.getEndTimeOfMonth(today), StatisticType.MONTH);
  }

  public void statisticPeriodMonth() {
    LocalDate today = LocalDate.now();
    LocalDate periodMonth = today.minusDays(today.getMonthValue());
    String date = periodMonth.getYear() + "-" + periodMonth.getMonthValue();
    statisticUpdate(date, Helper.getStartTimeOfMonth(periodMonth), Helper.getEndTimeOfMonth(periodMonth), StatisticType.MONTH);
  }

  public void statisticYear() {
    LocalDate today = LocalDate.now();
    String date = String.valueOf(today.getYear());
    statisticUpdate(date, Helper.getStartTimeOfYear(today), Helper.getEndTimeOfYear(today), StatisticType.YEAR);
  }

  public void statisticUpdate(String date, Long startAt, Long endAt, StatisticType statisticType) {
    List<ProductTransactionDetail> listProductTransactions = productTransactionDetailStorage.findByCreatedAtBetween(startAt, endAt);
    for (ProductTransactionDetail transaction : listProductTransactions) {
      String productId = transaction.getProductDetail().getProductId();
      ProductTransaction productTransaction = productTransactionStorage.findProductTransactionByIdInCache(productId);
      TransactionStatistic transactionStatistic = transactionStatisticStorage.findByDateAndProductIdAndStatisticType(date, productId, statisticType);
      if (Objects.isNull(transactionStatistic)) transactionStatistic = new TransactionStatistic();

      TotalStatisticResponse total = productTransactionDetailStorage.countByProductId(transaction.getProductDetail().getProductId(), startAt, endAt);
      transactionStatistic.setStatisticType(statisticType);
      transactionStatistic.setDate(date);
      transactionStatistic.setUsername(transaction.getStore().getOwnerStoreName());
      transactionStatistic.setProductId(productId);
      transactionStatistic.setTotalPrice(total.getTotalPrice());
      transactionStatistic.setTotalProduct(total.getQuantity());
      transactionStatistic.setTotalUser(productTransactionStorage.countUserBuy(productTransaction.getBuyerUsername(), productId));
      transactionStatisticStorage.save(transactionStatistic);
    }
  }


}
