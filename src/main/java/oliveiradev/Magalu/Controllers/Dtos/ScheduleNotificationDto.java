package oliveiradev.Magalu.Controllers.Dtos;

import java.time.LocalDateTime;

import oliveiradev.Magalu.Entities.Channel;
import oliveiradev.Magalu.Entities.Notification;
import oliveiradev.Magalu.Entities.Status;

public record ScheduleNotificationDto(
    LocalDateTime dateTime, 
    String destination, 
    String message, Channel.Values channel) {

    public Notification toNotification() {
        return new Notification(
            dateTime, destination, message, channel.toChannel(), Status.Values.PENDING.toStatus()
        );
    }
}
