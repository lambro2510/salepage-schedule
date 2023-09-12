package com.salespage.salepageschedule.domains.repositories;

import com.salespage.salepageschedule.domains.entities.UserFavorite;
import com.salespage.salepageschedule.domains.entities.types.FavoriteType;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFavoriteRepository extends MongoRepository<UserFavorite, ObjectId> {

  UserFavorite findByUsernameAndRefIdAndFavoriteType(String username, String productId, FavoriteType type);

  List<UserFavorite> findByUsernameAndFavoriteTypeAndLike(String username, FavoriteType favoriteType, Boolean isLike);
}
