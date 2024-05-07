package com.example.kafkademo.service;


import com.example.kafkademo.dto.KafkaEmail;
import com.example.kafkademo.helper.HtmlThymeleaf;
import com.example.kafkademo.utils.EmailUtils;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.util.Map;

import static com.example.kafkademo.helper.ReadJSONFile.HTML_REQUEST_APPROVED_ENG;
import static com.example.kafkademo.utils.EmailUtils.*;


@Service
public class EmailService {

    @Value("${spring.mail.verify.host}")
    private String host;

    @Value("${spring.mail.username}")
    private String fromEmail;

    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;

    public EmailService(JavaMailSender emailSender, TemplateEngine templateEngine) {
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }


    @Async
    public void sendSimpleMailMessage(String name, String to, String token) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            message.setTo(to);
            message.setFrom(fromEmail);
            message.setText(EmailUtils.getSimpleMessage(name));
            emailSender.send(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }


    @Async
    public void sendHtmlEmail(KafkaEmail kafkaEmail, String to) {
        try {
            Context context = new Context();

            String subject = HTML_REQUEST_APPROVED_ENG.subject;
            subject = subject.replace("{Request NO}", kafkaEmail.Process.getFirst().Number);

            String actualYear = LocalDateTime.now().getYear() + "";
            String footer = HTML_REQUEST_APPROVED_ENG.footer;
            footer = footer.replace("{Company Name}", kafkaEmail.CompanyName);
            footer = footer.replace("{Actual Year}", actualYear);

            String info = HTML_REQUEST_APPROVED_ENG.info;
            info = info.replace("{User Full Name}", kafkaEmail.Persons.getFirst().UserName);

            context.setVariables(Map.of(
                    "actualYear", actualYear,
                    "companyName", kafkaEmail.CompanyName,
                    "requestNo", kafkaEmail.Process.getFirst().Number,
                    "requester", kafkaEmail.Process.getFirst().Name,
                    "requestDate", kafkaEmail.Process.getFirst().localDateTime.toString(),
                    "header", HTML_REQUEST_APPROVED_ENG.header,
                    "noReply", HTML_REQUEST_APPROVED_ENG.noReply,
                    "info", info,
                    "footer", footer));
            String htmlText = templateEngine.process(kafkaEmail.EmailTemplateKey.toString(), context);
            MimeMessage message = getMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
            helper.setPriority(1);
            helper.setSubject(subject);
            helper.setTo(to);
            helper.setFrom(fromEmail);
            helper.setText(htmlText, true);
            emailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    protected MimeMessage getMimeMessage() {
        return emailSender.createMimeMessage();
    }

    private String getContentId(String filename) {
        return "<" + filename + ">";
    }
}
