package com.example.kafkademo.service;

import com.example.kafkademo.dto.EmailTemplate;
import com.example.kafkademo.dto.KafkaEmail;
import com.example.kafkademo.dto.Language;
import com.example.kafkademo.dto.Person;
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


    @KafkaListener(topics = "kafka", groupId = "topic-group")
    public void consume(ConsumerRecord<String, String> record) throws JsonProcessingException {


        KafkaEmail kafkaEmail = objectMapper.readValue(record.value(), KafkaEmail.class);
        kafkaEmail.Persons.add(new Person("Isgender Memmedov", "isgender.detroit@gmail.com", Language.ENG));
        kafkaEmail.ReferenceNo = "";

        sendAll(kafkaEmail);
    }

    public void sendAll(KafkaEmail kafkaEmail) {
        System.out.println("kafka template: " + kafkaEmail.EmailTemplateKey);
        try {
            kafkaEmail.Persons.forEach(z -> emailService.sendHtmlEmail(kafkaEmail, z));
        } catch (Exception e) {
            System.err.println("ae ConsumerService-de sendAll metodunda problem var" + e.getMessage());
        }
    }

}
