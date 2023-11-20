package com.salespage.salepageschedule.domains.service;

import com.salespage.salepageschedule.domains.config.AppConfig;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(value = "serverClient", url = "${feign.domain}", configuration = AppConfig.class)
public interface ServerService {
  @RequestLine("POST api/v1/it/statistic/today")
  void statisticToday();

  @RequestLine("POST api/v1/it/statistic/pre")
  void statisticPreDay();

  @RequestLine("POST api/v1/it/statistic/hot")
  void updateHotProduct();
}
