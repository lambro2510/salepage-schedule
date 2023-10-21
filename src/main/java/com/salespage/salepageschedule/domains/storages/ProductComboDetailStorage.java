package com.salespage.salepageschedule.domains.storages;

import com.salespage.salepageschedule.domains.entities.ProductComboDetail;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductComboDetailStorage extends BaseStorage{
  public List<ProductComboDetail> findByProductIdIn(List<String> ids) {
    return productComboDetailRepository.findByProductIdIn(ids);
  }

  public void saveAll(List<ProductComboDetail> productComboDetails) {
    productComboDetailRepository.saveAll(productComboDetails);
  }
}
