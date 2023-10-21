package com.salespage.salepageschedule.domains.entities;

import com.salespage.salepageschedule.domains.entities.BaseEntity;
import com.salespage.salepageschedule.domains.entities.infor.ProductInfo;
import com.salespage.salepageschedule.domains.entities.infor.Rate;
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
  private ObjectId id;

  @Field("product_name")
  private String productName;

  @Field("description")
  private String description;

  @Field("product_info")
  private List<ProductInfo> productInfos = new ArrayList<>();

  @Field("image_urls")
  private List<String> imageUrls = new ArrayList<>();

  @Field("default_image_url")
  private String defaultImageUrl;

  @Field("category_id")
  private String categoryId;

  @Field("rate")
  private Rate rate = new Rate();

  @Field("created_by")
  private String createdBy;

  @Field("seller_store_ids")
  private List<String> sellerStoreIds;

  @Field("is_hot")
  private Boolean isHot;

}
