package com.example.kafkademo.service;

import com.example.kafkademo.dto.EmailTemplate;
import com.example.kafkademo.dto.KafkaEmail;
import com.example.kafkademo.dto.Message;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class ProducerService {

    private final KafkaTemplate<String, KafkaEmail> kafkaTemplate;
    private final EmailService emailService;

    @Value("${kafka.topic}")
    private String topic;

    public ProducerService(KafkaTemplate<String, KafkaEmail> kafkaTemplate, EmailService emailService) {
        this.kafkaTemplate = kafkaTemplate;
        this.emailService = emailService;
    }

    public void send(KafkaEmail message) {
        kafkaTemplate.send(topic, message);
        System.out.println("gonderildi");
    }

    public void sendMessageAndEmail(KafkaEmail message) {
//        send(message);
//        message..forEach(z -> {
//            System.out.println(message);
//            System.out.println();
//            System.out.println(message.topic);
//            emailService.sendHtmlEmail(message.message, z,"", message.topic);
//        });
    }

    public void sendMessageAndEmail(Message message) {
//        send(message);
        message.toList.forEach(z -> {
            System.out.println(message);
            System.out.println();
            System.out.println(message.topic);
            emailService.sendHtmlEmail(message.message, z,"", message.topic);
        });
    }

//    @PostConstruct
//    public void init() {
//        List<String> emails = new ArrayList<>();
//        emails.add("fermanallahverdiyev31@gmail.com");
//        emails.add("isgender.detroit@gmail.com");
//        Message message = new Message("xyz", emails, "saalaaaaam");
//        sendMessageAndEmail(message);
//    }
}
