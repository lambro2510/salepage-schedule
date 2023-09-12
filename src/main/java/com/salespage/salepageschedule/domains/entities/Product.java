package com.salespage.salepageschedule.domains.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.salespage.salepageschedule.domains.entities.infor.Rate;
import com.salespage.salepageschedule.domains.entities.types.SizeType;
import com.salespage.salepageschedule.domains.entities.types.WeightType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Document("product")
@Data
public class Product extends BaseEntity {
  @Id
  @JsonSerialize(using = ToStringSerializer.class)
  private ObjectId id;

  @Field("product_name")
  private String productName;

  @Field("description")
  private String description;

  @Field("image_urls")
  private List<String> imageUrls = new ArrayList<>();

  @Field("default_image_url")
  private String defaultImageUrl;

  @Field("category_id")
  private String categoryId;

  @Field(value = "price")
  private Double price;

  @Field("rate")
  private Rate rate = new Rate();

  @Field("seller_username")
  private String sellerUsername;

  @Field("seller_store_ids")
  private List<String> sellerStoreIds;

  @Field("detail")
  private ProductDetail detail;


  @Data
  public static class ProductDetail {

    @Field("origin")
    String origin;

    @Field("is_foreign")
    Boolean isForeign;

    @Field("size")
    Long size;

    @Field("sizeType")
    SizeType sizeType;

    @Field("weight")
    Long weight;

    @Field("weightType")
    WeightType weightType;

    @Field("colors")
    List<String> colors;

    @Field("is_guarantee")
    Boolean isGuarantee;

    @Field("quantity")
    Long quantity = 0L;
  }
}
