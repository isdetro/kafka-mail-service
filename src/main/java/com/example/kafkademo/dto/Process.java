package com.example.kafkademo.dto;


public class Process {
    public String Number;
    public String Name;
    public String LocalDateTime;
    public String ReasonDescription;
    public int Sequence;


    public Process(String Number, String Name, String LocalDateTime, String ReasonDescription, int Sequence) {
     this.Number = Number;
     this.Name = Name;
     this.LocalDateTime = LocalDateTime;
     this.ReasonDescription = ReasonDescription;
     this.Sequence = Sequence;
    }

    public Process(String number, String name, String localDateTime, String reasonDescription) {
        Number = number;
        Name = name;
        LocalDateTime = localDateTime;
        ReasonDescription = reasonDescription;
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
