package com.salespage.salepageschedule.app.schedules;

import com.salespage.salepageschedule.domains.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class StatisticSchedule {

  @Autowired
  @Lazy
  private ServerService serverService;

//  @Scheduled(initialDelay = 70000, fixedDelay = 1000 * 60 * 60 * 4) //4h 1 lần
//  public void checkInStatistic() {
//    log.info("checkInStatistic -> {}",
//        LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")));
//    serverService.statisticUserCheckIn();
//  }

  @Scheduled(fixedDelay = 1000 * 30) //30s 1 lần
  public void productStatisticToday() {
    serverService.statisticToday();
  }

  @Scheduled(fixedDelay = 1000 * 60 * 60) //1h 1 lần
  public void productStatisticPreDay() {
    serverService.statisticPreDay();
  }

  @Scheduled(initialDelay = 5000, fixedDelay = 1000 * 30) //30s 1 lần
//  @Scheduled(cron = "0 */15 * * * *") // Mỗi 15 phút
  public void updateToHotProduct() {
    serverService.updateHotProduct();
  }
}