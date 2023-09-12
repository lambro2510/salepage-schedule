package com.salespage.salepageschedule.domains.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.salespage.salepageschedule.domains.entities.status.PaymentStatus;
import com.salespage.salepageschedule.domains.entities.types.PaymentType;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

@Document("payment_transaction")
@Data
public class PaymentTransaction extends BaseEntity {

  @Id
  @JsonSerialize(using = ToStringSerializer.class)
  private ObjectId id;

  @Field("username")
  private String username;

  @Field("description")
  private String description;

  @Field("amount")
  private Long amount;

  @Field("payment_type")
  private PaymentType type;

  @Field("bank_account_id")
  private String bankAccountId;

  @Field("payment_status")
  private PaymentStatus paymentStatus;

  public boolean createdOneDayPeriod() {
    return createdAt < (System.currentTimeMillis() - 24 * 60 * 60 * 1000);
  }
}
