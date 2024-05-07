package com.example.kafkademo.helper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import kotlin.jvm.internal.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ReadJSONFile implements CommandLineRunner {

    private final ObjectMapper objectMapper;
    public static HtmlThymeleaf HTML_REQUEST_APPROVED_ENG = null;

    public ReadJSONFile(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        JsonNode jsonNode;
        List<HtmlThymeleaf> htmlThymeleafs = new ArrayList<>();
        try(InputStream inputStream = TypeReference.class.getResourceAsStream("/json/email-eng.json")){
           jsonNode = objectMapper.readValue(inputStream, JsonNode.class);
        }catch (Exception e){
            throw new Exception("Error reading JSON file");
        }


        JsonNode edges = getRequestApproved(jsonNode);

        htmlThymeleafs.add(createHtmlFromJson(edges));
        System.out.println(htmlThymeleafs.get(0).header + " -----> header");
        System.out.println(htmlThymeleafs.get(0).noReply + " -----> noReply");
        System.out.println(htmlThymeleafs.get(0).info + " -----> info");
        System.out.println(htmlThymeleafs.get(0).footer + " -----> footer");

    }

    private HtmlThymeleaf createHtmlFromJson(JsonNode jsonNode) {
        String subject = jsonNode.get("subject").asText();
        String header = jsonNode.get("header").asText();
        String noReply = jsonNode.get("noReply").asText();
        String info = jsonNode.get("info").asText();
        String footer = jsonNode.get("footer").asText();
        HTML_REQUEST_APPROVED_ENG = new HtmlThymeleaf(subject,header,noReply,info,footer);
        return HTML_REQUEST_APPROVED_ENG;
    }

    private JsonNode getRequestApproved (JsonNode jsonNode) {
        return Optional.ofNullable(jsonNode)
                .map(x -> x.get("requestApproved"))
                .orElseThrow(() -> new RuntimeException("Json Invalid XXX"));
    }
}
