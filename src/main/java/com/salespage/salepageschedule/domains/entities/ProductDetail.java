package com.salespage.salepageschedule.domains.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

@EqualsAndHashCode(callSuper = true)
@Data
@Document("product_detail")
public class ProductDetail extends BaseEntity{

  @Id
  ObjectId id;

  @Field("product_id")
  String productId;

  @Field("type")
  ProductDetailType type;

  @Field("quantity")
  Integer quantity;

  @Field("origin_price")
  Double originPrice;

  @Field("sell_price")
  Double sellPrice;

  @Field("discount_percent")
  Double discountPercent;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ProductDetailType {

    @Field("type")
    String type;

    @Field("color")
    String color = "#FFFFFF";
  }

}
