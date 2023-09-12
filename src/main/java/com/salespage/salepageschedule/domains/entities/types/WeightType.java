package com.salespage.salepageschedule.domains.entities.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum WeightType {
  KILOGRAM("kg"),
  GRAM("g");

  String unit;
}
