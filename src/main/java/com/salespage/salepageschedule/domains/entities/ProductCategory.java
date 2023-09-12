package com.salespage.salepageschedule.domains.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.salespage.salepageschedule.domains.entities.types.CategoryType;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

@EqualsAndHashCode(callSuper = true)
@Document("product_category")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory extends BaseEntity {
  @Id
  @JsonSerialize(using = ToStringSerializer.class)
  private ObjectId id;

  @Field("category_name")
  private String categoryName;

  @Field("description")
  private String description;

  @Field("category_type")
  private CategoryType categoryType;

  @Field("product_type")
  private String productType;

  @Field("created_by")
  private String createdBy;

  @Field("updated_by")
  private String updatedBy;

}
