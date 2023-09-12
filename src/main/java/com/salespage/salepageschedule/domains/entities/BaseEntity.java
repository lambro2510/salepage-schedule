package com.salespage.salepageschedule.domains.entities;

import com.salespage.salepageschedule.domains.utils.DateUtils;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Data
public class BaseEntity implements Serializable {
  @Field(name = "created_at")
  protected Long createdAt = DateUtils.nowInMillis();

  @Field(name = "updated_at")
  protected Long updatedAt = DateUtils.nowInMillis();
}
