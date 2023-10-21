package com.salespage.salepageschedule.domains.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.salespage.salepageschedule.domains.entities.infor.Rate;
import com.salespage.salepageschedule.domains.entities.types.CurrencyType;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Calendar;
import java.util.Date;

@Document("user")
@Data
public class User {

  @Id
  @JsonSerialize(using = ToStringSerializer.class)
  private ObjectId id;

  @Indexed(unique = true)
  private String username;

  @Field("email")
  private String email;

  @Field("phone_number")
  private String phoneNumber;

  @Field("display_name")
  private String displayName;

  @Field("date_of_born")
  private Date dateOfBirth;

  @Field("first_name")
  private String firstName;

  @Field("last_name")
  private String lastName;

  @Field("image_url")
  private String imageUrl;

  @Field("rate")
  private Rate rate = new Rate();

  @Field("balance")
  private UserBalance balance;

  @Data
  public static class UserBalance {
    @Field("currency_unit")
    private CurrencyType type = CurrencyType.VND;

    @Field("money")
    private Double money = 0D;

    public void addMoney(double amount) {
      this.money = this.money + amount;
    }

    public void minusMoney(double amount) {
      money = money - amount;
      if (money < 0)
        money = 0D;
    }

  }

}
