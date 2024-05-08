package com.example.kafkademo.controller;

import com.example.kafkademo.service.ProducerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/producer")
@RestController
public class ProducerController {

    private final ProducerService producerService;

    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping
    public void getTest2() throws JsonProcessingException {
        producerService.init();
    }
}
