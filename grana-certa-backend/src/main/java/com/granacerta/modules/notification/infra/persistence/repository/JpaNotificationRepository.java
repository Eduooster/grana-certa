package com.granacerta.modules.notification.infra.persistence.repository;

import com.granacerta.modules.notification.infra.persistence.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface    JpaNotificationRepository extends JpaRepository<NotificationEntity, Long> {
}
