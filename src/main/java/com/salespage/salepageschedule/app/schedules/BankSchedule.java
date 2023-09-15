package com.salespage.salepageschedule.app.schedules;

import com.salespage.salepageschedule.domains.services.BankService;
import com.salespage.salepageschedule.domains.services.TpBankService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Log4j2
public class BankSchedule {

  @Autowired
  private BankService bankService;

  @Autowired
  private TpBankService tpBankService;

  @Scheduled(initialDelay = 10000 ,fixedDelay = 1000 * 15) //15s 1 lần đồng bộ
  public void saveBankTransaction() throws IOException {
    log.info("-----async transaction-----start");
    bankService.saveBankTransaction();
    log.info("-----async transaction-----end");
  }

  @Scheduled(initialDelay = 20000 ,fixedDelay = 1000 * 30) //30s 1 lần
  public void checkNotResolveTransaction() throws Exception {
    log.info("-----checkNotResolveTransaction-----start");
    bankService.checkNotResolveTransaction();
    log.info("-----checkNotResolveTransaction-----end");
  }

  @Scheduled(cron = "*/30 * * * * *")
  public void asyncTransactionToday() throws Exception {
    log.info("-----checkNotResolveTransaction-----start");
    tpBankService.saveTpBankTransactionToday();
    log.info("-----checkNotResolveTransaction-----end");
  }

  @Scheduled(cron = "0 */30 * * * *")
  public void asyncTransactionPreDay() throws Exception {
    log.info("-----checkNotResolveTransaction-----start");
    tpBankService.saveTpBankTransactionPeriodDay();
    log.info("-----checkNotResolveTransaction-----end");
  }
}
