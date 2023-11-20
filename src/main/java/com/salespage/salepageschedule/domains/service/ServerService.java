package com.salespage.salepageschedule.domains.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "serverClient", url = "${server.domain}")
public interface ServerService {
  @RequestMapping(method = RequestMethod.POST, value = "api/v1/it/today")
  Boolean statisticToday();

  @RequestMapping(method = RequestMethod.POST, value = "api/v1/it/pre")
  Boolean statisticPreDay();

  @RequestMapping(method = RequestMethod.POST, value = "api/v1/it/hot")
  Boolean updateHotProduct();
}
