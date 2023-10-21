package com.salespage.salepageschedule.domains.entities;

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
  private ObjectId id;

  @Field("category_name")
  private String categoryName;

  @Field("category_type")
  private CategoryType categoryType;

  @Field("description")
  private String description;

  @Field("product_type_id")
  private String productTypeId;

  @Field("rangeAge")
  private String rangeAge;

  @Field("created_by")
  private String createdBy;

  @Field("updated_by")
  private String updatedBy;

}
