package com.salespage.salepageschedule.domains.storages;

import com.salespage.salepageschedule.domains.entities.Product;
import com.salespage.salepageschedule.domains.utils.CacheKey;
import lombok.extern.log4j.Log4j2;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@Log4j2
public class ProductStorage extends BaseStorage {
  public void save(Product product) {
    productRepository.save(product);
  }

  public Product findProductById(String productId) {
    return productRepository.findProductById(new ObjectId(productId));
  }

  public Page<Product> findAll(Query query, Pageable pageable) {
    return productRepository.findAll(query, pageable);

  }

  public void delete(String productId) {
    productRepository.deleteById(new ObjectId(productId));
  }

  public List<Product> findBySellerStoreIdsContaining(String storeId) {
    return productRepository.findBySellerStoreIdsContaining(storeId);
  }

  public void saveAll(List<Product> products) {
    productRepository.saveAll(products);
  }

  public void saveAllWithCache(List<Product> products) {
    productRepository.saveAll(products);

  }

  public Long countProduct() throws Exception {
    Long numberProduct = remoteCacheManager.get(CacheKey.getNumberProduct(), Long.class);
    if (Objects.isNull(numberProduct)) {
      numberProduct = productRepository.count();
      remoteCacheManager.set(CacheKey.getNumberProduct(), numberProduct.toString(), 3600);
    }
    return numberProduct;
  }


  public List<Product> findTop11ByCategoryIdIn(List<String> categoriesId) {
    return productRepository.findTop11ByCategoryIdIn(categoriesId);
  }

  public List<Product> findByCategoryId(String categoryId) {
    return productRepository.findByCategoryId(categoryId);
  }

  public List<Product> findTop10ByCategoryIdOrderByCreatedAtDesc(String typeName) {
    return productRepository.findTop10ByCategoryIdOrderByCreatedAtDesc(typeName);
  }

  public List<Product> findAll() {
    return productRepository.findAll();
  }

  public boolean isExistByProductId(String refId) {
    return productRepository.existsById(new ObjectId(refId));
  }

}
