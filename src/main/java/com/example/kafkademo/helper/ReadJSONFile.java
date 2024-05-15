package com.example.kafkademo.helper;

import com.example.kafkademo.dto.EmailTemplate;
import com.example.kafkademo.dto.KafkaEmail;
import com.example.kafkademo.dto.Language;
import com.example.kafkademo.dto.Person;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import kotlin.jvm.internal.TypeReference;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Optional;

@Component
public class ReadJSONFile {

    private final ObjectMapper objectMapper;

    public ReadJSONFile(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public HtmlThymeleaf getRequestHTML(KafkaEmail kafkaEmail, Person p) {

        try {
            JsonNode jsonNode = getWhichJsonFile(p.Language.name());
            jsonNode = getRequest(jsonNode, kafkaEmail.EmailTemplateKey);
            HtmlThymeleaf htmlThymeleaf = createHtmlFromJson(jsonNode, kafkaEmail.EmailTemplateKey.name(), p.Language.name());

            return htmlThymeleaf;
        } catch (Exception e) {
            System.err.println("ReadJsonFile-da getRequestHtml metodundadi problem");
            throw new RuntimeException(e);

        }

    }

    public JsonNode getWhichJsonFile(String language) throws Exception {
        JsonNode jsonNode;
        System.out.println(language + " lanquic");
        if (language.equalsIgnoreCase("ENG")) {
            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/json/email-eng.json")) {
                jsonNode = objectMapper.readValue(inputStream, JsonNode.class);
                return jsonNode;
            } catch (Exception e) {
                System.err.println("ReadJsonFile-da getWhichJson metodundadi problem");

                throw new Exception("Error reading JSON file");
            }
        }
        else if (language.equalsIgnoreCase(Language.AZE.name())) {
            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/json/email-aze.json")) {
                jsonNode = objectMapper.readValue(inputStream, JsonNode.class);
                return jsonNode;
            } catch (Exception e) {
                throw new Exception("Error reading JSON file");
            }
        }
        else {
            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/json/email-rus.json")) {
                jsonNode = objectMapper.readValue(inputStream, JsonNode.class);
                return jsonNode;
            } catch (Exception e) {
                throw new Exception("Error reading JSON file");
            }
        }

    }

    public HtmlThymeleaf createHtmlFromJson(JsonNode jsonNode, String type, String language) {
        String subject = jsonNode.get("subject").asText();
        String header = jsonNode.get("header").asText();
        String noReply = jsonNode.get("noReply").asText();
        String info = jsonNode.get("info").asText();
        String footer = jsonNode.get("footer").asText();

        System.out.println("subject: " + subject);
        System.out.println("header: " + header);
        System.out.println("noReply: " + noReply);
        System.out.println("info: " + info);
        System.out.println("footer: " + footer);

        return new HtmlThymeleaf(subject, header, noReply, info, footer, type, language);
    }

    public JsonNode getRequest(JsonNode jsonNode, EmailTemplate emailTemplate) {

        String firstLetter = emailTemplate.toString().charAt(0) + "";
        String temp = firstLetter.toLowerCase() + emailTemplate.toString().substring(1);

        System.out.println();
        System.out.println(temp + "  Template");
        System.out.println();

        return Optional.ofNullable(jsonNode)
                .map(x -> x.get(temp))
                .orElseThrow(() -> new RuntimeException("Json Invalid!"));
    }


}
