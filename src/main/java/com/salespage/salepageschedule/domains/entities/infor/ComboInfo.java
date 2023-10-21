package com.salespage.salepageschedule.domains.entities.infor;

import com.salespage.salepageschedule.domains.entities.ProductCombo;
import com.salespage.salepageschedule.domains.entities.types.DiscountType;
import lombok.Data;

@Data
public class ComboInfo {
  String comboId;

  String comboName;

  DiscountType discountType;

  Double value;

  Double totalDiscount;

  Double sellPrice;

  public ComboInfo(ProductCombo combo, Double discount, Double price){
    comboId = combo.getId().toHexString();
    comboName = combo.getComboName();
    discountType = combo.getType();
    value = combo.getValue();
    totalDiscount = discount;
    sellPrice = price;
  }
}
