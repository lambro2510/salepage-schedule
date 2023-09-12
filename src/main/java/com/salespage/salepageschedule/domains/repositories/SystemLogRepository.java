package com.salespage.salepageschedule.domains.repositories;

import com.salespage.salepageschedule.domains.entities.SystemLog;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemLogRepository extends MongoRepository<SystemLog, ObjectId> {
}
