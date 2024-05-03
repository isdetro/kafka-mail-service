package com.example.kafkademo.service;

import com.example.kafkademo.dto.Message;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final EmailService emailService;

    @Value("${kafka.topic}")
    private String topic;

    public ProducerService(KafkaTemplate<String, Object> kafkaTemplate, EmailService emailService) {
        this.kafkaTemplate = kafkaTemplate;
        this.emailService = emailService;
    }

    public void send(Object message) {
        kafkaTemplate.send(topic, message);
        System.out.println("gonderildi");
    }

//    public void sendMessageAndEmail(Message message) {
//        send(message);
//        message.toList.forEach(z -> {
//            System.out.println(message.message);
//            System.out.println(z);
//            System.out.println(message.topic);
//            emailService.sendSimpleMailMessage(message.message, z, message.topic);
//        });
//    }

//    @PostConstruct
//    public void init() {
//        List<String> emails = new ArrayList<>();
//        emails.add("isgender.detroit@gmail.com");
//        Message message = new Message("xyz", emails, "saalaaaaam");
//        sendMessageAndEmail(message);
//    }
}
