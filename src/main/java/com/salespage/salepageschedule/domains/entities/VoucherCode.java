package com.salespage.salepageschedule.domains.entities;

import com.salespage.salepageschedule.domains.entities.status.VoucherCodeStatus;
import com.salespage.salepageschedule.domains.utils.DateUtils;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Document("voucher_code")
@Data
@CompoundIndex(name = "code_owner_status_index", def = "{'code' : 1, 'ownerId' : 1, 'voucherCodeStatus' : 1}", unique = true)
public class VoucherCode extends BaseEntity {

  @Id
  private ObjectId id;

  @Field("voucher_store_id")
  private String voucherStoreId;

  @Field("owner_id")
  private String username;

  @Field("used_at")
  private Date userAt;

  @Field("code")
  private String code;

  @Field("expire_time")
  private LocalDate expireTime;

  @Field("voucher_code_status")
  private VoucherCodeStatus voucherCodeStatus = VoucherCodeStatus.NEW;



  public boolean checkVoucher(String username){
    return expireTime.isAfter(DateUtils.now().toLocalDate()) && voucherCodeStatus.equals(VoucherCodeStatus.OWNER) && Objects.equals(username, this.getUsername());
  }
}