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

  @Scheduled(initialDelay = 70000 ,fixedDelay = 1000 * 60 * 60 * 4) //4h 1 lần
  public void checkInStatistic() {
    log.info("checkInStatistic -> {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")));
    checkInDailyStatisticService.statisticUserCheckIn();
  }

  @Scheduled(cron = "*/30 * * * * *") //30 1 lần
  public void paymentStatisticToday() {
    log.info("paymentStatistic new-> {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")));
//    paymentStatisticService.asyncStatisticToday();
  }

  @Scheduled(cron = "* */1 * * * *") //1p 1 lần
  public void paymentStatisticPreDay() {
    log.info("paymentStatistic new-> {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")));
    paymentStatisticService.asyncStatisticPreDay();
  }

  @Scheduled(cron = "0 0 * * * *") // Mỗi 1 giờ
  public void updateToNormalProduct() {
    log.info("updateToNormalProduct new-> {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")));
    paymentStatisticService.updateToNormalProduct();
  }

  @Scheduled(cron = "0 */15 * * * *") // Mỗi 15 phút
  public void updateToHotProduct() {
    log.info("updateToHotProduct new-> {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")));
    paymentStatisticService.updateToHotProduct();
  }

}
