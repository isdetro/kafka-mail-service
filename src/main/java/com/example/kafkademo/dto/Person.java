package com.example.kafkademo.dto;

public class Person {
    public String UserName;
    public String Email;
    public Language Lang;

    public Person(String userName, String email, Language Lang) {
        UserName = userName;
        Email = email;
        this.Lang = Lang;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "UserName='" + UserName + '\'' +
                ", Email='" + Email + '\'' +
                ", Language=" + Lang +
                '}';
    }
}