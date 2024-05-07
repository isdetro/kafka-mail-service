package com.example.kafkademo.service;

import com.example.kafkademo.dto.EmailTemplate;
import com.example.kafkademo.dto.KafkaEmail;
import com.example.kafkademo.dto.Message;
import com.example.kafkademo.dto.Person;
import com.example.kafkademo.dto.Process;
import com.example.kafkademo.helper.ReadJSONFile;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class ProducerService {

    private final KafkaTemplate<String, KafkaEmail> kafkaTemplate;
    private final EmailService emailService;

    @Value("${kafka.topic}")
    private String topic;

//    @Value("classpath:resources/json/*.json")
//    private Resource[] procedureResource;

    public ProducerService(KafkaTemplate<String, KafkaEmail> kafkaTemplate, EmailService emailService) {
        this.kafkaTemplate = kafkaTemplate;
        this.emailService = emailService;
    }

    public void send(KafkaEmail message) {
        kafkaTemplate.send(topic, message);
        System.out.println("gonderildi");
    }

    public void sendMessageAndEmail(KafkaEmail message) {
        message.EmailTemplateKey = EmailTemplate.RequestApproved;
        send(message);

        message.Persons.forEach(z -> {
            System.out.println(message);
            System.out.println();
            System.out.println(z.Email);
            emailService.sendHtmlEmail(message, z.Email);
        });
    }

//    public void sendMessageAndEmail(Message message) {
////        send(message);
//        message.toList.forEach(z -> {
//            System.out.println(message);
//            System.out.println();
//            System.out.println(message.topic);
//            emailService.sendHtmlEmail(message.message, z,"", message.topic);
//        });
//    }

    public void init() {
        List<Person> emails = new ArrayList<>();
//        emails.add(new Person("fermanallahverdiyev31@gmail.com","fermanallahverdiyev31@gmail.com"));
        emails.add(new Person("isgender.detroit@gmail.com","isgender.detroit@gmail.com"));
        KafkaEmail kafkaEmail = new KafkaEmail();
        kafkaEmail.EmailTemplateKey = EmailTemplate.RequestApproved;
        kafkaEmail.Persons =emails;
        kafkaEmail.CompanyName = "Apertech";
        kafkaEmail.Sequence = 69;
        List<Process> processes = new ArrayList<>();
        LocalDateTime localDateTime = LocalDateTime.now();
        Process process = new Process("21657165E", kafkaEmail.Persons.getFirst().UserName, localDateTime);
        Process process1 = new Process("21657165E", kafkaEmail.Persons.getFirst().UserName, localDateTime);
        Process process2 = new Process("21657165E", kafkaEmail.Persons.getFirst().UserName, localDateTime);
        processes.add(process);
        processes.add(process1);
        processes.add(process2);
        kafkaEmail.Process = processes;


        sendMessageAndEmail(kafkaEmail);
    }
}
