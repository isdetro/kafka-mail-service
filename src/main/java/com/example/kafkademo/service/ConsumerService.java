package com.example.kafkademo.service;

import com.example.kafkademo.dto.KafkaEmail;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
//@Slf4j
public class ConsumerService {

    private final EmailService emailService;

    public ConsumerService(EmailService emailService) {
        this.emailService = emailService;
    }


    @KafkaListener(topics = "Procurement")
    public void consume(ConsumerRecord<String, String> record) {
        System.out.println(record.value());
        System.out.println(record);
//        record.value().Persons.forEach(z -> {
//            System.out.println(z.UserName);
//            System.out.println(z.Email);
//            emailService.sendSimpleMailMessage(record.value().Link, z.Email, record.value().Link);
//        });
    }

}
