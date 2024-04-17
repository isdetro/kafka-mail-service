package com.example.kafkademo.controller;

import com.example.kafkademo.service.ListenerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/listener")
public class ListenerController {

    private final ListenerService listenerService;

    public ListenerController(ListenerService listenerService) {
        this.listenerService = listenerService;
    }

    @Value("${kafka.topic}")
    private String topic;


    @GetMapping
    public String listen(String data) {
        return listenerService.consume(data);
    }

}
