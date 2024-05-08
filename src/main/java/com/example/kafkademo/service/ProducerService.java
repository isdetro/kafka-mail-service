package com.example.kafkademo.service;

import com.example.kafkademo.dto.*;
import com.example.kafkademo.dto.Process;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class ProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic}")
    private String topic;

    public ProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(KafkaEmail message) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(message);
        kafkaTemplate.send(topic, json);
        System.out.println("gonderildi");
    }

    public void init() {
        List<Person> emails = new ArrayList<>();
        emails.add(new Person("Isgender Detroit","isgender.detroit@gmail.com", Language.ENG));
//        emails.add(new Person("Isgender Nashville","isgender.nashville@gmail.com", Language.ENG));
//        emails.add(new Person("Isgandar Mammadov","isgandar.mammadov97@gmail.com", Language.ENG));
        KafkaEmail kafkaEmail = new KafkaEmail();
        kafkaEmail.EmailTemplateKey = EmailTemplate.BidApproved;
        kafkaEmail.Persons =emails;
        kafkaEmail.CompanyName = "Apertech";
        kafkaEmail.Sequence = 69;
        LocalDateTime localDateTime = LocalDateTime.now();
        List<Process> processes = new ArrayList<>();

        emails.forEach(x -> {
            Process process = new Process("21657165E", x.UserName, localDateTime.toString()," SPAM");
            Process process1 = new Process("21657165W", x.UserName, localDateTime.toString(), "SPAM");
            Process process2 = new Process("21657165A", x.UserName, localDateTime.toString(), "SPAM");
            processes.add(process);
            processes.add(process1);
            processes.add(process2);
            kafkaEmail.Process = processes;

        });
        try {
            send(kafkaEmail);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
