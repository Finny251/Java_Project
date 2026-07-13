package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class NotificationRequest {

    @NotBlank(message = "Username cannot be empty")
    private String username;

    @NotBlank(message = "Channel type cannot be empty")
    @Pattern(regexp = "^(EMAIL|SMS)$", message = "Channel type must be either EMAIL or SMS")
    private String channelType;

    @NotBlank(message = "Message text payload cannot be empty")
    private String message;
}
