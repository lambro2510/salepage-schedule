package com.salespage.salepageschedule;

import com.salespage.salepageschedule.domains.service.ServerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableFeignClients
public class SalesPageScheduleApplication {

  public static void main(String[] args) {
    SpringApplication.run(SalesPageScheduleApplication.class, args);
  }

}
