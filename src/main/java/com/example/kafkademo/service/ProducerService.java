package com.example.kafkademo.service;

import com.example.kafkademo.dto.*;
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
        emails.add(new Person("Isgender Memmedov","isgender.detroit@gmail.com", Language.ENG));
//        emails.add(new Person("Isgender Nashville","isgender.nashville@gmail.com", Language.ENG));
//        emails.add(new Person("Isgandar Mammadov","isgandar.mammadov97@gmail.com", Language.ENG));
        KafkaEmail kafkaEmail = new KafkaEmail();
        kafkaEmail.EmailTemplateKey = EmailTemplate.RegistrationIsPending;
        kafkaEmail.Persons =emails;
        kafkaEmail.CompanyName = "Apertech";
        kafkaEmail.rowInfos.getFirst().Sequence = 2;
        String localDateTime = LocalDateTime.now().toString().substring(0,10);
        List<RowInfo> processes = new ArrayList<>();

        emails.forEach(x -> {
            RowInfo process = new RowInfo("21657165E", "Isgender Memmedov", localDateTime," SPAM");
            RowInfo process1 = new RowInfo("21657165W", "Oktay Afandi", localDateTime, "SPAM");
            RowInfo process2 = new RowInfo("21657165A", "Subhan Mesimov", localDateTime, "SPAM");
            processes.add(process);
            processes.add(process1);
            processes.add(process2);
            kafkaEmail.rowInfos = processes;

        });

        try {
            send(kafkaEmail);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
