package com.example.kafkademo.dto;

public class Person {
    public String UserName;
    public String Email;
    public Language language;

    public Person(String userName, String email, Language language) {
        UserName = userName;
        Email = email;
        this.language = language;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "UserName='" + UserName + '\'' +
                ", Email='" + Email + '\'' +
                '}';
    }
}