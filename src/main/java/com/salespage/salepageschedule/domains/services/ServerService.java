package com.salespage.salepageschedule.domains.services;

import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(value = "serverClient", url = "${feign.domain}")
public interface ServerService {
  @RequestLine("POST api/v1/it/statistic/today")
  void statisticToday();

  @RequestLine("POST api/v1/it/statistic/pre")
  void statisticPreDay();

  @RequestLine("POST api/v1/it/statistic/hot")
  void updateHotProduct();

  @RequestLine("POST api/v1/it/bank/mb/today")
  void updateMbToday();

  @RequestLine("POST api/v1/it/bank/tp/today")
  void updateTpToday();

  @RequestLine("POST api/v1/it/bank/tp/pre")
  void updateTpPre();
}
