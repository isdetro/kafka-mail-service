package com.example.kafkademo.service;


import com.example.kafkademo.dto.KafkaEmail;
import com.example.kafkademo.dto.Person;
import com.example.kafkademo.dto.Process;
import com.example.kafkademo.helper.HtmlThymeleaf;
import com.example.kafkademo.helper.ReadJSONFile;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.kafkademo.utils.EmailUtils.*;


@Service
public class EmailService {

    @Value("${spring.mail.verify.host}")
    private String host;

    @Value("${spring.mail.username}")
    private String fromEmail;

    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;
    private final  ReadJSONFile readJSONFile ;

    public EmailService(JavaMailSender emailSender, TemplateEngine templateEngine, ReadJSONFile readJSONFile) {
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
        this.readJSONFile = readJSONFile;
    }

    @Async
    public void sendHtmlEmail(KafkaEmail kafkaEmail, Person p) {
        try {
            List<Process> onlyProcess = new ArrayList<>();
            kafkaEmail.Process.forEach(process -> {
                if(process.Name.equals(p.UserName)){
                    onlyProcess.add(process);
                }
            });

            HtmlThymeleaf htmlThymeleaf = readJSONFile.getRequestHTML(kafkaEmail, p);


            Context context = new Context();
            String subject = htmlThymeleaf.subject;
//            subject = subject.replace("{Seq}", Integer.toString(kafkaEmail.Process.getFirst().Sequence));


            String actualYear = LocalDateTime.now().getYear() + "";
            String footer = htmlThymeleaf.footer;
            footer = footer.replace("{Company Name}", kafkaEmail.CompanyName);
            footer = footer.replace("{Actual Year}", actualYear);

            String info = htmlThymeleaf.info;
            info = info.replace("{User Full Name}", p.UserName);
//            info = info.replace("{Reason Description}", kafkaEmail.Process.getFirst().ReasonDescription);
            info = info.replace("{Vendor Name or Company Name}", kafkaEmail.CompanyName);
            String link = "\"" + kafkaEmail.Link + "\"";
            info = info.replace("\"#\"", link);

            context.setVariables(Map.of(
                    "actualYear", actualYear,
                    "companyName", kafkaEmail.CompanyName,
                    "request", onlyProcess,
                    "header", htmlThymeleaf.header,
                    "noReply", htmlThymeleaf.noReply,
                    "info", info,
                    "footer", footer));
            String htmlText = templateEngine.process(kafkaEmail.EmailTemplateKey.toString(), context);
            MimeMessage message = getMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
            helper.setPriority(1);
            helper.setSubject(subject);
            helper.setTo(p.Email);
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
