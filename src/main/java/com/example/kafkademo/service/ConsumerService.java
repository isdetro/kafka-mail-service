package com.example.kafkademo.service;

import com.example.kafkademo.dto.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConsumerService {

    private final EmailService emailService;

    private final ObjectMapper objectMapper;

    public ConsumerService(EmailService emailService, ObjectMapper objectMapper) {
        this.emailService = emailService;
        this.objectMapper = objectMapper;
    }


    @KafkaListener(topics = "kafka", groupId = "topic-group")
    public void consume(ConsumerRecord<String, String> record) throws JsonProcessingException {


        KafkaEmail kafkaEmail = objectMapper.readValue(record.value(), KafkaEmail.class);
        kafkaEmail.ReferenceNo = "";

        sendAll(kafkaEmail);
    }

    @KafkaListener(topics = "sola-mail", groupId = "topic-group")
    public void consumeADY(ConsumerRecord<String, String> record) throws JsonProcessingException {


        KafkaEmail kafkaEmail = objectMapper.readValue(record.value(), KafkaEmail.class);
        kafkaEmail.ReferenceNo = "";

        sendAll(kafkaEmail);
    }

    public void sendAll(KafkaEmail kafkaEmail) {
        System.out.println("kafka template: " + kafkaEmail.EmailTemplateKey);
        try {
            if (kafkaEmail.rowInfos != null) {
                kafkaEmail.Persons.forEach(z -> emailService.sendHtmlEmail(kafkaEmail, z));
            }else {
                List<RowInfo> rowInfos = new ArrayList<>();
                RowInfo rowInfo = new RowInfo();
                rowInfos.add(rowInfo);
                kafkaEmail.rowInfos = rowInfos;
                kafkaEmail.Persons.forEach(z -> emailService.sendHtmlEmail(kafkaEmail, z));

            }
        } catch (Exception e) {
            System.err.println("ae ConsumerService-de sendAll metodunda problem var" + e.getMessage());
        }
    }

}
