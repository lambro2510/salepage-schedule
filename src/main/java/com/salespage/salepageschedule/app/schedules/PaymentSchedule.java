package com.salespage.salepageschedule.app.schedules;

import com.salespage.salepageschedule.domains.services.ServerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class PaymentSchedule {
  @Autowired
  private ServerService serverService;

  @Scheduled(fixedDelay = 1000 * 30)
  public void productStatisticToday() {
    log.info("process productStatisticToday: {}", System.currentTimeMillis());
    serverService.updateMbToday();
  }

  @Scheduled(fixedDelay = 1000 * 30)
  public void updateTpToday() {
    log.info("process updateTpToday: {}", System.currentTimeMillis());
    serverService.updateTpToday();
  }

  @Scheduled(fixedDelay = 1000 * 60 * 60)
  public void updateTpPre() {
    log.info("process updateTpPre: {}", System.currentTimeMillis());
    serverService.updateTpPre();
  }

  @Scheduled(fixedDelay = 1000 * 30)
  public void processMbPayment() {
    log.info("process updateTpPre: {}", System.currentTimeMillis());
    serverService.processMbTransaction();
  }
}