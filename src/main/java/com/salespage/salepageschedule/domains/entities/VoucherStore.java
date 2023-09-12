package com.salespage.salepageschedule.domains.entities;

import com.salespage.salepageschedule.domains.entities.status.VoucherStoreStatus;
import com.salespage.salepageschedule.domains.entities.types.DiscountType;
import com.salespage.salepageschedule.domains.entities.types.VoucherStoreType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@EqualsAndHashCode(callSuper = true)
@Document("voucher_store")
@Data
public class VoucherStore extends BaseEntity {
  @Id
  private ObjectId id;

  @Field("voucher_store_name")
  private String voucherStoreName;

  @Field("voucher_store_type")
  private VoucherStoreType voucherStoreType;

  @Field("ref_id")
  private String refId;

  @Field("discount_type")
  private DiscountType discountType;

  @Field("value")
  private Long value;

  @Field("voucher_store_detail")
  private VoucherStoreDetail voucherStoreDetail;

  @Field("voucher_store_status")
  private VoucherStoreStatus voucherStoreStatus = VoucherStoreStatus.INACTIVE;

  @Field("created_by")
  private String createdBy;

  @Data
  public static class VoucherStoreDetail {

    @Field("quantity")
    private Long quantity = 0L;

    @Field("quantity_used")
    private Long quantityUsed = 0L;

    @Field("max_able_price")
    private Long maxAblePrice; //Giá trị sản phẩm tối đa voucher dạng sale có thể sử dụng

    @Field("min_able_price")
    private Long minAblePrice; //Giá trị sản phẩm tối thiểu voucher dạng sale có thể sử dụng

    @Field("max_voucher_per_user")
    private Long maxVoucherPerUser;
  }
}
