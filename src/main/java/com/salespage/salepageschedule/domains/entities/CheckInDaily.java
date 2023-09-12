package com.salespage.salepageschedule.domains.entities;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

@Document("check_in_daily")
@Data
public class CheckInDaily extends BaseEntity {
  @Id
  private ObjectId id;

  @Field("date")
  private String date;

  @Field("username")
  private String username;

  @Field("check_in")
  private Boolean checkInToday = false;
}
