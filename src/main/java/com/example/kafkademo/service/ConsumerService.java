package com.example.kafkademo.service;

import com.example.kafkademo.dto.KafkaEmail;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    private final EmailService emailService;

    private final ObjectMapper objectMapper;

    public ConsumerService(EmailService emailService, ObjectMapper objectMapper) {
        this.emailService = emailService;
        this.objectMapper = objectMapper;
    }


    @KafkaListener(topics = "newTopic", groupId = "topic-group")
    public void consume(ConsumerRecord<String, String> record) throws JsonProcessingException {
        String json = record.value();
        KafkaEmail kafkaEmail = objectMapper.readValue(json, KafkaEmail.class);
        kafkaEmail.Persons.forEach(z -> emailService.sendHtmlEmail(kafkaEmail, z));
    }

}
