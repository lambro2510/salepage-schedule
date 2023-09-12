package com.salespage.salepageschedule.domains.services;

import com.salespage.salepageschedule.domains.entities.Notification;
import com.salespage.salepageschedule.domains.entities.status.NotificationStatus;
import com.salespage.salepageschedule.domains.entities.types.NotificationType;
import org.springframework.stereotype.Service;

@Service
public class NotificationService extends BaseService {

  public void createNotification(String username, String title, String content, NotificationType notificationType, String refId) {
    Notification notification = new Notification();
    notification.setUsername(username);
    notification.setTitle(title);
    notification.setContent(content);
    notification.setNotificationType(notificationType);
    notification.setRefId(refId);
    notification.setNotificationStatus(NotificationStatus.NOT_SEEN);
    notificationStorage.save(notification);
  }

}
