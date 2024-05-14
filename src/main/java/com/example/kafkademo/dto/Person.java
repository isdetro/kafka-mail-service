package com.example.kafkademo.dto;

public class Person {
    public String UserName;
    public String Email;
    public Language Language;

    public Person(String userName, String email, com.example.kafkademo.dto.Language Language) {
        UserName = userName;
        Email = email;
        this.Language = Language;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "UserName='" + UserName + '\'' +
                ", Email='" + Email + '\'' +
                ", Language=" + Language +
                '}';
    }
}