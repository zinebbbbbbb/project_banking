package com.example.demo.Kafka;

import com.example.demo.Transaction.TransferConfirmation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransferProducer {
    private final KafkaTemplate<String, TransferConfirmation> kafkaTemplate;
    public void SendTransferConfirmation(TransferConfirmation transferConfirmation){
        log.info("Sending Transfer Confirmation");
        Message<TransferConfirmation> message = MessageBuilder
                .withPayload(transferConfirmation)
                .setHeader(KafkaHeaders.TOPIC, "transfer-topic")
                .build();
        kafkaTemplate.send(message);
    }


}
