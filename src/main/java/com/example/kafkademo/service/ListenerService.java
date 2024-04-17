package com.example.kafkademo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class ListenerService {

    private final Logger logger = LoggerFactory.getLogger(ListenerService.class);

    @KafkaListener(topics = "quickstart-events", groupId = "group-id")
    public String consume(String data) {
       logger.info(String.format("data: " + data));
       return data;
    }


}
