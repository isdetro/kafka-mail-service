package com.example.kafkademo.service;


import com.example.kafkademo.dto.KafkaEmail;
import com.example.kafkademo.dto.Person;
import com.example.kafkademo.helper.HtmlThymeleaf;
import com.example.kafkademo.helper.ReadJSONFile;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
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

//    @Async
    public void sendHtmlEmail(KafkaEmail kafkaEmail, Person p) {
        System.out.println(p + " personsss");
        try {
            HtmlThymeleaf htmlThymeleaf = readJSONFile.getRequestHTML(kafkaEmail, p);


            Context context = new Context();
            String subject = htmlThymeleaf.subject;

            System.out.println("Yekun subject = " + subject);


            String actualYear = LocalDateTime.now().getYear() + "";
            String footer = htmlThymeleaf.footer;
            if (footer.contains("{Company Name}")){
                footer = footer.replace("{Company Name}", kafkaEmail.CompanyName);
            }
            if (footer.contains("{Actual Year}")){
                footer = footer.replace("{Actual Year}", actualYear);
            }

            System.out.println("Yekun footer = " + footer);


            String info = htmlThymeleaf.info;
            if (info.contains("{User Full Name}")){
                info = info.replace("{User Full Name}", p.UserName);
            }
            if (info.contains("{Reason Description}")){
                info = info.replace("{Reason Description}", kafkaEmail.rowInfos.getFirst().ReasonDescription);
            }
            if (info.contains("{Vendor Name or Company Name}")){
                info = info.replace("{Vendor Name or Company Name}", kafkaEmail.CompanyName);
            }
            String link = "\"" + kafkaEmail.Link + "\"";
            if(info.contains("\"#\"")){
                info = info.replace("\"#\"", link);
//                if (kafkaEmail.EmailTemplateKey.name().contains(kafkaEmail.EmailTemplateKey.EmailVerification.name())){
//                    info = info.replace("\"#\"", "");
//                }
//                else {
//                    info = info.replace("\"#\"", link);
//                }
            }

            System.out.println("Yekun info = " + info);


            context.setVariables(Map.of(
                    "actualYear", actualYear,
                    "companyName", kafkaEmail.CompanyName,
                    "request", kafkaEmail.rowInfos,
                    "header", htmlThymeleaf.header,
                    "noReply", htmlThymeleaf.noReply,
                    "info", info,
                    "footer", footer));
            System.out.println();
            System.out.println(context + "  context");
            String htmlText = templateEngine.process(kafkaEmail.EmailTemplateKey.toString(), context);
            MimeMessage message = getMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
            helper.setPriority(2);
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
