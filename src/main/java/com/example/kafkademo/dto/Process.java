package com.example.kafkademo.dto;


public class Process {
    public String Number;
    public String Name;
    public String LocalDateTime;
    public String ReasonDescription;


    public Process(String number, String name, String localDateTime, String reasonDescription) {
        Number = number;
        Name = name;
        LocalDateTime = localDateTime;
        ReasonDescription = reasonDescription;
    }

    public Process(String number, String name, String localDateTime) {
        Number = number;
        Name = name;
        LocalDateTime = localDateTime;
    }

    public Process() {
    }

    @Override
    public String toString() {
        return "Process{" +
                "Number='" + Number + '\'' +
                ", Name='" + Name + '\'' +
                ", LocalDateTime='" + LocalDateTime + '\'' +
                '}';
    }
}
