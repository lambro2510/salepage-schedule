package com.salespage.salepageschedule.domains.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.time.LocalDate;

@Document("statistic_checkpoint")
@Data
public class StatisticCheckpoint {

  @Id
  private String id;

  @Field("check_point")
  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate checkPoint;
}