package com.salespage.salepageschedule.domains.repositories;

import com.salespage.salepageschedule.domains.entities.Rating;
import com.salespage.salepageschedule.domains.entities.types.RatingType;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends MongoRepository<Rating, ObjectId> {
  Rating findByUsernameAndRefIdAndAndRatingType(String username, String productId, RatingType ratingType);
}
