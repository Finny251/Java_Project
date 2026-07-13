package com.example.demo.channel;

import com.example.demo.model.User;

public interface NotificationChannel {

    boolean send(User recipient, String message);

    String getChannelName();

}