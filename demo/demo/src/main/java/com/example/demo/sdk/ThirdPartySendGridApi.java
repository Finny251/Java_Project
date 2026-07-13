package com.example.demo.sdk;

import org.springframework.stereotype.Component;

@Component
public class ThirdPartySendGridApi {

    public int transmitHtmlEmail(String targetEmailAddress,
                                 String htmlBody) {

        System.out.println(
                "[SendGrid SDK] Sending Email to "
                        + targetEmailAddress);

        return 202;

    }

}