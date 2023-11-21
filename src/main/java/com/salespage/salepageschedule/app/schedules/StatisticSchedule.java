package com.salespage.salepageschedule.app.schedules;

import com.salespage.salepageschedule.domains.services.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class StatisticSchedule {
  @Autowired
  @Lazy
  private ServerService serverService;

  @Scheduled(fixedDelay = 1000 * 30)
  public void productStatisticToday() {
    serverService.statisticToday();
  }

  @Scheduled(fixedDelay = 1000 * 60 * 60)
  public void productStatisticPreDay() {
    serverService.statisticPreDay();
  }

  @Scheduled(initialDelay = 5000, fixedDelay = 1000 * 30)
  public void updateToHotProduct() {
    serverService.updateHotProduct();
  }
}
