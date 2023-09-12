package com.salespage.salepageschedule;

import com.salespage.salepageschedule.domains.repositories.base.impl.MongoResourceRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableMongoRepositories(repositoryBaseClass = MongoResourceRepositoryImpl.class)
public class SalesPageScheduleApplication {

  public static void main(String[] args) {
    SpringApplication.run(SalesPageScheduleApplication.class, args);
  }

}
