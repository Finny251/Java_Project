package com.example.demo.observer;

import com.example.demo.event.NotificationEvent;
import com.example.demo.model.NotificationLog;
import com.example.demo.repository.NotificationLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AuditLoggerObserver {

    private final NotificationLogRepository logRepository;

    @Autowired
    public AuditLoggerObserver(NotificationLogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @EventListener
    public void onNotificationProcessed(NotificationEvent event) {
        System.out.println("[Observer: Audit Log] Writing transaction log to PostgreSQL...");

        NotificationLog log = NotificationLog.builder()
                .recipient(event.getRecipient())
                .channelType(event.getChannelType())
                .sentContent(event.getMessage())
                .status(event.getStatus())
                .failureReason(event.getErrorReason())
                .build();

        logRepository.save(log);
    }
}
