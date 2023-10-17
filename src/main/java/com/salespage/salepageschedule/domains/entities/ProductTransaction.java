package com.salespage.salepageschedule.domains.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.salespage.salepageschedule.domains.entities.infor.ComboInfo;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Document("product_transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductTransaction extends BaseEntity {

  @Id
  @JsonSerialize(using = ToStringSerializer.class)
  private ObjectId id;

  @Field("buyer_username")
  @Indexed(name = "buyer_username_idx")
  private String buyerUsername;

  @Field("total_price")
  private Double totalPrice;

  @Field("product_transaction_details")
  private List<ProductTransactionDetail> productTransactionDetails;

  @Field("note")
  private String note;

  @Field("combo_info")
  private ComboInfo comboInfo;



}
