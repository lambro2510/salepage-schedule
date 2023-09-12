package com.salespage.salepageschedule.domains.services;

import com.salespage.salepageschedule.domains.entities.SystemLog;
import com.salespage.salepageschedule.domains.entities.types.LogType;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SystemLogService {
  public void createSystemLog(String createdBy, String message, String trace, LogType logType) {
    SystemLog systemLog = new SystemLog();
    systemLog.setLogType(logType);
    systemLog.setMessage(message);
    systemLog.setTrace(trace);
    systemLog.setCreatedBy(createdBy);
    systemLog.setCreatedAt(LocalDateTime.now());
  }
}
