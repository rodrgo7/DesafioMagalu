package oliveiradev.Magalu.Repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import oliveiradev.Magalu.Entities.Notification;
import oliveiradev.Magalu.Entities.Status;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByStatusInAndDateTimeBefore(List<Status> statuses, LocalDateTime dateTime);
}