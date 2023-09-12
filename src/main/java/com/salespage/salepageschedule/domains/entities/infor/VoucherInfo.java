package com.salespage.salepageschedule.domains.entities.infor;

import com.salespage.salepageschedule.domains.entities.types.DiscountType;
import com.salespage.salepageschedule.domains.entities.types.VoucherStoreType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoucherInfo {
  private String voucherCode;
  private VoucherStoreType voucherStoreType;
  private DiscountType discountType;
  private Double totalDiscount;
  private Double priceBefore;
  private Double priceAfter;
}
