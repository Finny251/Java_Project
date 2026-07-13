package com.example.demo.event;

import com.example.demo.model.User;
import lombok.Getter;

@Getter
public class NotificationEvent {
    private final User recipient;
    private final String channelType;
    private final String message;
    private final String status;
    private final String errorReason;

    public NotificationEvent(User recipient, String channelType, String message, String status, String errorReason) {
        this.recipient = recipient;
        this.channelType = channelType;
        this.message = message;
        this.status = status;
        this.errorReason = errorReason;
    }
}
