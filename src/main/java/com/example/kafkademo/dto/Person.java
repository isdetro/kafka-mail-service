package com.example.kafkademo.dto;

public class Person {
    public String UserName;
    public String Email;

    public Person(String userName, String email) {
        UserName = userName;
        Email = email;
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