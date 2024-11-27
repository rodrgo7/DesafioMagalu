package oliveiradev.Magalu.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.stereotype.Service;

import oliveiradev.Magalu.Controllers.Dtos.ScheduleNotificationDto;
import oliveiradev.Magalu.Entities.Notification;
import oliveiradev.Magalu.Entities.Status;
import oliveiradev.Magalu.Repositories.NotificationRepository;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
 
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void scheduleNotification(ScheduleNotificationDto dto) {
        notificationRepository.save(dto.toNotification());
    }

    public Optional<Notification> findById(Long notificationId) {
       return notificationRepository.findById(notificationId);
    }

    public void cancelNotification(Long notificationId) {
        var notification = findById(notificationId);

        if (notification.isPresent()) {
            notification.get().setStatus(Status.Values.CANCELED.toStatus());
            notificationRepository.save(notification.get());
        }
    }

    public void checkAndSend(LocalDateTime dateTime) {
        var notifications = notificationRepository.findByStatusInAndDateTimeBefore(List.of(
            Status.Values.PENDING.toStatus(),
            Status.Values.ERROR.toStatus()),
        dateTime);

       notifications.forEach(sendNotification());
    }

    private Consumer<? super Notification> sendNotification() {
        return n -> {
            n.setStatus(Status.Values.SUCESS.toStatus());
            notificationRepository.save(n);
        };
    }
}
