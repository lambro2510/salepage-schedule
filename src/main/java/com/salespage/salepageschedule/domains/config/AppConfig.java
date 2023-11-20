package com.salespage.salepageschedule.domains.config;

import feign.Contract;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
  @Bean
  public Contract feignContract() {
    return new feign.Contract.Default();
  }

  @Bean
  public OkHttpClient client() {
    return new OkHttpClient();
  }
}
