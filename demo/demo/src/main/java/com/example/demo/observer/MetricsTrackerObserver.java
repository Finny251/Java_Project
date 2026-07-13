package com.example.demo.observer;

import com.example.demo.event.NotificationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MetricsTrackerObserver {

    @EventListener
    public void onNotificationProcessed(NotificationEvent event) {
        System.out.println("[Observer: Metrics] Incrementing counters. Channel: " + event.getChannelType() + " | State: " + event.getStatus());
    }
}
