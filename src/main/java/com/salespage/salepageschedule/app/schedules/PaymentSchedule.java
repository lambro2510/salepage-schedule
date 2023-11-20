package com.salespage.salepageschedule.app.schedules;

import com.salespage.salepageschedule.domains.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PaymentSchedule {
  @Autowired
  @Lazy
  private ServerService serverService;

  @Scheduled(fixedDelay = 1000 * 30) //30s 1 lần
  public void productStatisticToday() {
    serverService.updateMbToday();
  }

  @Scheduled(fixedDelay = 1000 * 30) //30s 1 lần
  public void updateTpToday() {
    serverService.updateTpToday();
  }

  @Scheduled(fixedDelay = 1000 * 60 * 60) //1h 1 lần
  public void updateTpPre() {
    serverService.updateTpPre();
  }
}
