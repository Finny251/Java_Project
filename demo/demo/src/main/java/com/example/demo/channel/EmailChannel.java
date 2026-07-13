package com.example.demo.channel;

import com.example.demo.model.User;
import com.example.demo.sdk.ThirdPartySendGridApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("EMAIL")
public class EmailChannel implements NotificationChannel {

    private final ThirdPartySendGridApi sendGridApi;

    @Autowired
    public EmailChannel(ThirdPartySendGridApi sendGridApi) {
        this.sendGridApi = sendGridApi;
    }

    @Override
    public boolean send(User recipient, String message) {
        try {
            // Adapting our architecture payload into SendGrid HTTP response validations
            int statusCode = sendGridApi.transmitHtmlEmail(recipient.getEmail(), message);
            return statusCode == 202;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getChannelName() { return "EMAIL"; }
}
