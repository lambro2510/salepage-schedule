package com.salespage.salepageschedule.domains.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.salespage.salepageschedule.app.responses.Statistic.TotalPaymentStatisticResponse;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Document("payment_statistic")
@Data
public class ProductStatistic {
  @Id
  private ObjectId id;

  @Field("daily")
  @JsonFormat(pattern = "dd-MM-yyyy")
  @Indexed(name = "daily_index", unique = true)
  private LocalDate daily;

  @Field("product_id")
  private String productId;

  @Field("product_name")
  private String productName;

  @Field("total_user")
  private Long totalUser = 0L;

  @Field("total_view")
  private Long totalView = 0L;

  @Field("total_buy")
  private Long totalBuy = 0L;

  @Field("total_purchase")
  private Long totalPurchase = 0L;

  @Field("total_shipper_cod")
  private Long totalShipperCod = 0L;

  public void partnerFromStatistic(TotalPaymentStatisticResponse response){
    totalUser = response.getTotalUser();
    totalBuy = response.getTotalBuy();
    totalPurchase = response.getTotalPurchase();
    totalShipperCod = response.getTotalShipCod();
  }

  public void create(){
    totalUser = 0L;
    totalView = 0L;
    totalBuy = 0L;
    totalPurchase = 0L;
    totalShipperCod = 0L;
  }
}