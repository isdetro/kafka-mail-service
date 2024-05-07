package com.example.kafkademo.dto;

import java.time.LocalDateTime;

public class Process {
    public String Number;
    public String Name;
    public LocalDateTime localDateTime;

    public Process(String number, String name, LocalDateTime localDateTime) {
        Number = number;
        Name = name;
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString() {
        return "Process{" +
                "Number='" + Number + '\'' +
                ", Name='" + Name + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
