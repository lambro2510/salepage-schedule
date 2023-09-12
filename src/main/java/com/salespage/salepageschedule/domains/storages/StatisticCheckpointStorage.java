package com.salespage.salepageschedule.domains.storages;

import com.salespage.salepageschedule.domains.entities.StatisticCheckpoint;
import org.springframework.stereotype.Component;

@Component
public class StatisticCheckpointStorage extends BaseStorage {
  public StatisticCheckpoint findById(String transactionCheckpointId) {
    return statisticCheckpointRepository.findStatisticCheckpointById(transactionCheckpointId);
  }

  public void save(StatisticCheckpoint statisticCheckpoint) {
    statisticCheckpointRepository.save(statisticCheckpoint);
  }
}
