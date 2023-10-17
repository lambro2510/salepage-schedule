package com.salespage.salepageschedule.domains.entities;

import com.salespage.salepageschedule.domains.entities.status.ProductTypeStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

@EqualsAndHashCode(callSuper = true)
@Document("product_type")
@Data
public class ProductType extends BaseEntity {

  @Id
  private ObjectId id;

  @Indexed(name = "product_type_idx")
  @Field(name = "product_type")
  private String productType;

  @Field(name = "product_type_name")
  private String productTypeName;

  @Field(name = "status")
  private ProductTypeStatus status;

  @Field(name = "description")
  private String description;

  @Field(name = "created_by")
  private String createdBy;

  @Field(name = "updated_by")
  private String updatedBy;


}
