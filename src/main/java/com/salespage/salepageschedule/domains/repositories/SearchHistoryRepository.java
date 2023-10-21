package com.salespage.salepageschedule.domains.repositories;

import com.salespage.salepageschedule.domains.entities.SearchHistory;
import com.salespage.salepageschedule.domains.entities.types.SearchType;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SearchHistoryRepository extends MongoRepository<SearchHistory, ObjectId> {
  SearchHistory findByUsernameAndSearchTypeAndSearchData(String username, SearchType searchType, String productName);

  List<SearchHistory> findByUsernameOrderByCreatedAtDesc(String username);

  List<SearchHistory> findTop10ByUsernameOrderByCreatedAtDesc(String username);
}
