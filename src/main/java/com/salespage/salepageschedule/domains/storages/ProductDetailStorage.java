package com.salespage.salepageschedule.domains.storages;

import com.salespage.salepageschedule.domains.entities.ProductDetail;
import com.salespage.salepageschedule.domains.utils.Helper;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDetailStorage extends BaseStorage{
  public List<ProductDetail> findByProductId(String productId) {
    return productDetailRepository.findByProductId(productId);
  }

  public ProductDetail findById(String detailId) {
    return productDetailRepository.findById(new ObjectId(detailId)).get();
  }

  public void save(ProductDetail productDetail) {
    productDetailRepository.save(productDetail);
  }

  public void delete(ProductDetail productDetail) {
    productDetailRepository.delete(productDetail);
  }

  public List<ProductDetail> findAll() {
    return productDetailRepository.findAll();
  }

  public List<ProductDetail> findByIdIn(List<String> ids) {
    return productDetailRepository.findByIdIn(Helper.convertListStringToListObjectId(ids));

  }
}
