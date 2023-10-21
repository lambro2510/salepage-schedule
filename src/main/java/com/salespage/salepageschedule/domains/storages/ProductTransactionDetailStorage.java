package com.salespage.salepageschedule.domains.storages;


import com.salespage.salepageschedule.app.responses.transactionResponse.TotalStatisticResponse;
import com.salespage.salepageschedule.domains.entities.ProductTransactionDetail;
import com.salespage.salepageschedule.domains.entities.types.ProductTransactionState;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductTransactionDetailStorage extends BaseStorage{
  public void saveAll(List<ProductTransactionDetail> transactionDetails) {
    productTransactionDetailRepository.saveAll(transactionDetails);
  }

  public List<ProductTransactionDetail> findProductTransactionByState(ProductTransactionState state) {
    return productTransactionDetailRepository.findByState(state);
  }

  public List<ProductTransactionDetail> findByCreatedAtBetween(Long startAt, Long endAt) {
    return productTransactionDetailRepository.findByCreatedAtBetween(startAt, endAt);
  }

  public TotalStatisticResponse countByProductId(String id, Long startAt, Long endAt) {
    Criteria criteria = Criteria.where("product_id").is(id)
        .andOperator(Criteria.where("created_at").gte(startAt), Criteria.where("created_at").lte(endAt));
    AggregationOperation match = Aggregation.match(criteria);
    GroupOperation groupOperation = Aggregation.group()
        .sum("total_price").as("totalPrice")
        .sum("quantity").as("quantity");
    Aggregation aggregation = Aggregation.newAggregation(match, groupOperation);
    AggregationResults<TotalStatisticResponse> result = mongoTemplate.aggregate(aggregation, "product_transaction_detail", TotalStatisticResponse.class);
    TotalStatisticResponse response = new TotalStatisticResponse();
    if (result.getUniqueMappedResult() != null) {
      response = result.getUniqueMappedResult();
    }
    return response;
  }
}
