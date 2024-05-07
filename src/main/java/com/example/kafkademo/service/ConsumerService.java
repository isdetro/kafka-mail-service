package com.example.kafkademo.service;

import com.example.kafkademo.dto.KafkaEmail;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
//@Slf4j
public class ConsumerService {

    private final EmailService emailService;

    private final ObjectMapper objectMapper;

    public ConsumerService(EmailService emailService, ObjectMapper objectMapper) {
        this.emailService = emailService;
        this.objectMapper = objectMapper;
    }


//    @KafkaListener(topics = "commerceTopic", groupId = "topic-group")
//    public void consume(ConsumerRecord<String, String> record) throws JsonProcessingException {
//        System.out.println(record.value());
//        System.out.println(record);
//       String json = record.value();
//       KafkaEmail kafkaEmail = objectMapper.readValue(json, KafkaEmail.class);
//        System.out.println(kafkaEmail.Persons.get(0).Email + " email");
//        System.out.println(kafkaEmail.EmailTemplateKey + " emailTemplateKey" );
//
//
//        kafkaEmail.Persons.forEach(z -> {
//            System.out.println(z.UserName);
//            System.out.println(z.Email);
//            emailService.sendHtmlEmail(kafkaEmail, z.Email);
//        });
//    }

    @KafkaListener(topics = "newTopic", groupId = "topic-group")
    public void consumeObject(ConsumerRecord<String, KafkaEmail> record) throws JsonProcessingException {
        System.out.println(record.value());
        System.out.println(record);
        System.out.println(record.value().Persons.get(0).Email + " email");
        System.out.println(record.value().EmailTemplateKey + " emailTemplateKey" );


        record.value().Persons.forEach(z -> {
            System.out.println(z.UserName);
            System.out.println(z.Email);
            emailService.sendHtmlEmail(record.value(), z.Email);
        });
    }



}
