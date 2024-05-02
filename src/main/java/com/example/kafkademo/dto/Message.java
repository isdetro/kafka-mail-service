package com.example.kafkademo.dto;

import java.util.List;

public class Message {
    public String topic;
    public List<String> toList;
    public String message;

    public Message(String topic, List<String> toList, String message) {
        this.topic = topic;
        this.toList = toList;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "topic='" + topic + '\'' +
                ", toList=" + toList +
                ", message='" + message + '\'' +
                '}';
    }

    public Message(){}
}
