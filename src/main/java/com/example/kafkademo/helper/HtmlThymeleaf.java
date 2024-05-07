package com.example.kafkademo.helper;

public class HtmlThymeleaf {
    public String subject;
    public String header;
    public String noReply;
    public String info;
    public String footer;

    public HtmlThymeleaf(String subject, String header, String noReply, String info, String footer) {
        this.subject = subject;
        this.header = header;
        this.noReply = noReply;
        this.info = info;
        this.footer = footer;
    }
}
