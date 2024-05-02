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

    @Value("${kafka.topic}")
    private String topic;

    public ProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(Object message) {
        kafkaTemplate.send(topic, message);
        System.out.println("gonderildi");
    }

    @PostConstruct
    public void init() {
        List<String> emails = new ArrayList<>();
        emails.add("isgender.detroit@gmail.com");
        Message message = new Message("",emails,"saalaaaaam");
        send(message);
    }
}
