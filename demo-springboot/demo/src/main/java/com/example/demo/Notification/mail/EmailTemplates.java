package com.example.demo.Notification.mail;

import lombok.Getter;

public enum EmailTemplates {

    TRANSFER_CONFIRMATION("transfer-confirmation.html", "Payment successfully processed")
    ;

    @Getter
    private final String template;
    @Getter
    private final String subject;


    EmailTemplates(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }
}
