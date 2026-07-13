package com.example.demo.channel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ChannelFactory {

    private final Map<String, NotificationChannel> channels;

    @Autowired
    public ChannelFactory(Map<String, NotificationChannel> channels) {
        this.channels = channels;
    }

    public NotificationChannel getChannel(String channelType) {

        NotificationChannel channel =
                channels.get(channelType.toUpperCase());

        if (channel == null) {
            throw new IllegalArgumentException(
                    "Unsupported notification channel : "
                            + channelType
            );
        }

        return channel;

    }

}