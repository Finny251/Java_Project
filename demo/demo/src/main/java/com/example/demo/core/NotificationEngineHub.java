package com.example.demo.core;

import com.example.demo.channel.ChannelFactory;
import com.example.demo.channel.NotificationChannel;
import com.example.demo.event.NotificationEvent;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class NotificationEngineHub {

    private final ChannelFactory channelFactory;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public NotificationEngineHub(ChannelFactory channelFactory, ApplicationEventPublisher eventPublisher) {
        this.channelFactory = channelFactory;
        this.eventPublisher = eventPublisher;
    }

    public boolean processNotification(User recipient, String channelType, String message) {
        NotificationChannel channel = channelFactory.getChannel(channelType);

        boolean isSuccess = channel.send(recipient, message);
        String status = isSuccess ? "SUCCESS" : "FAILED";
        String failureReason = isSuccess ? null : "Vendor network timeout exception";

        // Observer Pattern trigger step
        eventPublisher.publishEvent(new NotificationEvent(recipient, channelType, message, status, failureReason));

        return isSuccess;
    }
}
