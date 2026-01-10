package com.example.demo.Notification;


import com.example.demo.Notification.mail.EmailService;
import com.example.demo.mongo.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static java.lang.String.format;
@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationsConsumer {

    private final NotificationRepository repository;
    private final EmailService emailService;

    @KafkaListener(topics = "transfer-topic")
    public void consumeTransferConfirmation(TransferConfirmation confirmation) {
        log.info("Consumed transfer confirmation: {}", confirmation);

        repository.save(
                Notification.builder()
                        .type(NotificationType.Transformation_confirmation)
                        .notificationDate(LocalDateTime.now())
                        .transferConfirmation(confirmation)
                        .build()
        );

        try {
            emailService.sendTransferReceivedEmail(confirmation);
        } catch (Exception e) {
            log.error("Failed to send transfer email", e);
        }
    }
}
