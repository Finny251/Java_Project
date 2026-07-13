package com.example.demo.sdk;

import org.springframework.stereotype.Component;

@Component
public class ThirdPartyTwilioApi {

    public void dispatchSmsText(String destinationPhone,
                                String textPayload) {

        System.out.println(
                "[Twilio SDK] Sending SMS to "
                        + destinationPhone);

    }

}