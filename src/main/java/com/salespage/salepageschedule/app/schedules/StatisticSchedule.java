package com.salespage.salepageschedule.app.schedules;

import com.salespage.salepageschedule.domains.services.CheckInDailyStatisticService;
import com.salespage.salepageschedule.domains.services.PaymentStatisticService;
import com.salespage.salepageschedule.domains.services.TransactionStatisticService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Log4j2
public class StatisticSchedule {

  @Autowired
  private TransactionStatisticService transactionStatisticService;

  @Autowired
  private CheckInDailyStatisticService checkInDailyStatisticService;

  @Autowired
  private PaymentStatisticService paymentStatisticService;

  @Scheduled(initialDelay = 5000 ,fixedDelay = 1000 * 30) //30s 1 lần
  public void asyncTransactionStatisticToday() {
    log.info("asyncTransactionStatisticToday -> {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")));
    transactionStatisticService.statisticToday();
  }

  @Scheduled(initialDelay = 10000 ,fixedDelay = 1000 * 60 * 60 * 4) //4h 1 lần
  public void asyncTransactionStatisticPeriodDay() {
    log.info("asyncTransactionStatisticPeriodDay -> {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")));
    transactionStatisticService.statisticPeriodDate();
  }

  @Scheduled(initialDelay = 20000 ,fixedDelay = 1000 * 60 * 10) //10p 1 lần
  public void asyncTransactionStatisticWeek() {
    log.info("asyncTransactionStatisticWeek -> {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")));
    transactionStatisticService.statisticWeek();
  }

  @Scheduled(initialDelay = 30000 ,fixedDelay = 1000 * 60 * 60 * 24) //1 ngày 1 lần
  public void asyncTransactionStatisticPeriodWeek() {
    log.info("asyncTransactionStatisticPeriodWeek -> {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")));
    transactionStatisticService.statisticPeriodWeek();
  }

  @Scheduled(initialDelay = 40000 ,fixedDelay = 1000 * 60 * 60 * 24) //30s 1 lần
  public void asyncTransactionStatisticMonth() {
    log.info("asyncTransactionStatisticMonth -> {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")));
    transactionStatisticService.statisticMonth();
  }

  @Scheduled(initialDelay = 50000 ,fixedDelay = 1000 * 60 * 60 * 24 * 7) //1 tuần 1 lần
  public void asyncTransactionStatisticPeriodMonth() {
    log.info("asyncTransactionStatisticPeriodMonth -> {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")));
    transactionStatisticService.statisticPeriodMonth();
  }

  @Scheduled(initialDelay = 60000 ,fixedDelay = 1000 * 60 * 60 * 24 * 10) //10 ngày 1 lần
  public void asyncTransactionStatisticYear() {
    log.info("asyncTransactionStatisticYear -> {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")));
    transactionStatisticService.statisticYear();
  }

  @Scheduled(initialDelay = 70000 ,fixedDelay = 1000 * 60 * 60 * 4) //4h 1 lần
  public void checkInStatistic() {
    log.info("checkInStatistic -> {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")));
    checkInDailyStatisticService.statisticUserCheckIn();
  }

  @Scheduled(cron = "*/30 * * * * *") //1p 1 lần
  public void paymentStatisticToday() {
    log.info("paymentStatistic new-> {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")));
    paymentStatisticService.asyncStatisticToday();
  }

  @Scheduled(cron = "* */30 * * * *") //1p 1 lần
  public void paymentStatisticPreDay() {
    log.info("paymentStatistic new-> {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")));
    paymentStatisticService.asyncStatisticPreDay();
  }
}
