package com.example.demo.Notification.mail;

import com.example.demo.Notification.TransferConfirmation;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static com.example.demo.Notification.mail.EmailTemplates.TRANSFER_CONFIRMATION;
import static java.nio.charset.StandardCharsets.UTF_8;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendTransferReceivedEmail(TransferConfirmation transferConfirmation)
            throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(
                mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                UTF_8.name()
        );

        helper.setFrom("zinebbellefkih88@gmail.com");
        helper.setTo(transferConfirmation.receiverEmail());
        helper.setSubject(TRANSFER_CONFIRMATION.getSubject());

        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", transferConfirmation.receiverEmail()); // later replace with fullName
        variables.put("senderEmail", transferConfirmation.senderEmail());
        variables.put("amount", transferConfirmation.amount());
        variables.put("description", transferConfirmation.description());
        variables.put("sourceAccountNumber", transferConfirmation.sourceAccountNumber());
        variables.put("targetAccountNumber", transferConfirmation.targetAccountNumber());
        variables.put("timestamp", transferConfirmation.timestamp());

        Context context = new Context();
        context.setVariables(variables);

        String html = templateEngine.process(
                TRANSFER_CONFIRMATION.getTemplate(),
                context
        );

        helper.setText(html, true);
        mailSender.send(mimeMessage);

        log.info("Transfer email sent to {}", transferConfirmation.receiverEmail());
    }
}
