package com.example.demo.controller;

import com.example.demo.core.NotificationEngineHub;
import com.example.demo.dto.NotificationRequest;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private final UserRepository userRepository;
    private final NotificationEngineHub engineHub;

    @Autowired
    public NotificationController(UserRepository userRepository, NotificationEngineHub engineHub) {
        this.userRepository = userRepository;
        this.engineHub = engineHub;
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendNotification(@Valid @RequestBody NotificationRequest request) {
        // Find recipient user profile or fail gracefully
        User recipient = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User matching username '" + request.getUsername() + "' does not exist."));

        // Process request through the Singleton Hub
        boolean processingOutcome = engineHub.processNotification(recipient, request.getChannelType(), request.getMessage());

        if (processingOutcome) {
            return ResponseEntity.ok(Map.of("status", "SUCCESS", "message", "Notification delivered and logged successfully."));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("status", "FAILED", "message", "Delivery system failure. Check database tracking stack trace logs."));
        }
    }
}
