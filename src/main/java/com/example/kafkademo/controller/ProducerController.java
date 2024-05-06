package com.example.kafkademo.controller;

import com.example.kafkademo.dto.KafkaEmail;
import com.example.kafkademo.dto.Message;
import com.example.kafkademo.service.ProducerService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/producer")
@RestController
public class ProducerController {

    private final ProducerService producerService;

    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

//    @PostMapping
//    public String getTest(@RequestBody String message){
//       producerService.send(message);
//       return "gonderildi";
//    }

    @PostMapping
    public KafkaEmail getTest2(@RequestBody KafkaEmail message){
        producerService.send(message);
        return message;
    }
}
