package com.salespage.salepageschedule.domains.storages;

import com.salespage.salepageschedule.domains.entities.CheckInDailyStatistic;
import org.springframework.stereotype.Component;

@Component
public class CheckInDailyStatisticStorage extends BaseStorage {

  public CheckInDailyStatistic findByUsernameAndMonth(String username, String month) {
    return checkInDailyStatisticRepository.findByUsernameAndMonth(username, month);
  }

  public void save(CheckInDailyStatistic checkInDailyStatistic) {
    checkInDailyStatisticRepository.save(checkInDailyStatistic);
  }
}
