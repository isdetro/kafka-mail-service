package com.example.kafkademo.service;

import com.example.kafkademo.dto.Message;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
//@Slf4j
public class ConsumerService {

    @KafkaListener(topics = "${kafka.topic}")
    public void consume(ConsumerRecord<String, Message> record) {
        System.out.println(record.value());
    }

}
