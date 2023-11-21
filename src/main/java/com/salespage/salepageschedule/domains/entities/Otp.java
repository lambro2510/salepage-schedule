package com.salespage.salepageschedule.domains.entities;

import com.salespage.salepageschedule.domains.entities.types.OtpStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@EqualsAndHashCode(callSuper = true)
@Document("otp")
@Data
@AllArgsConstructor
public class Otp extends BaseEntity{
  @Id
  ObjectId id;

  @Field("phone_number")
  private String phoneNumber;

  @Field("otp")
  private String otp;

  @Field("status")
  private OtpStatus status;

}