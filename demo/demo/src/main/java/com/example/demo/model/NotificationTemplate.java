package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notification_templates")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "template_key", nullable = false, unique = true)
    private String templateKey; // e.g., "WELCOME_TXT", "OTP_ALERT"

    @Column(nullable = false, length = 1000)
    private String content; // e.g., "Hello {name}, your code is {otp}."
}
