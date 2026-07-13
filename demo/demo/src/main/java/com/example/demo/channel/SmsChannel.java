package com.example.demo.channel;

import com.example.demo.model.User;
import com.example.demo.sdk.ThirdPartyTwilioApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("SMS")
public class SmsChannel implements NotificationChannel {

    private final ThirdPartyTwilioApi twilioApi;

    @Autowired
    public SmsChannel(ThirdPartyTwilioApi twilioApi) {
        this.twilioApi = twilioApi;
    }

    @Override
    public boolean send(User recipient, String message) {
        try {
            // Adapting our parameter types to match Twilio SDK requirements
            twilioApi.dispatchSmsText(recipient.getPhoneNumber(), message);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getChannelName() { return "SMS"; }
}
