package com.salespage.salepageschedule.domains.entities;

import com.salespage.salepageschedule.domains.entities.status.NotificationStatus;
import com.salespage.salepageschedule.domains.entities.types.NotificationType;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

@Data
@Document("notification")
public class Notification extends BaseEntity {
  @Id
  private ObjectId id;

  @Field("username")
  private String username;

  @Field("title")
  private String title;

  @Field("content")
  private String content;

  @Field("notification_status")
  private NotificationStatus notificationStatus;

  @Field("notification_type")
  private NotificationType notificationType;

  @Field("ref_id")
  private String refId;

}
