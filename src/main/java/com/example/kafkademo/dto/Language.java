package com.example.kafkademo.dto;

public enum Language {

    AZE("Azerbaijan"),
    RUS("Russian"),
    ENG("English");

    private final String val;

    Language(String val) {
        this.val = val;
    }

}
