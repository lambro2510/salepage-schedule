package com.salespage.salepageschedule.app.schedules;

import com.salespage.salepageschedule.domains.services.ServerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Log4j2
public class StatisticSchedule {
  @Autowired
  private ServerService serverService;

  @Scheduled(fixedDelay = 1000 * 5)
  public void productStatisticToday() {
    log.info("process productStatisticToday: {}", System.currentTimeMillis());

    serverService.statisticToday();
  }

  @Scheduled(fixedDelay = 1000 * 60 * 60)
  public void productStatisticPreDay() {
    log.info("process productStatisticPreDay: {}", System.currentTimeMillis());

    serverService.statisticPreDay();
  }

  @Scheduled(initialDelay = 5000, fixedDelay = 1000 * 30)
  public void updateToHotProduct() {
    log.info("process updateToHotProduct: {}", System.currentTimeMillis());

    serverService.updateHotProduct();
  }
}
