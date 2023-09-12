package com.salespage.salepageschedule.domains.repositories;

import com.salespage.salepageschedule.domains.entities.Notification;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NotificationRepository extends MongoRepository<Notification, ObjectId> {
  Page<Notification> findByUsername(String username, Pageable pageable);

  Notification findNotificationById(String notificationId);
}
