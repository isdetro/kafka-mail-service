package com.example.kafkademo.dto;


public class RowInfo {
    public String Number;
    public String Name;
    public String LocalDateTime;
    public String ReasonDescription;
    public Integer Sequence;


    public RowInfo(String Number, String Name, String LocalDateTime, String ReasonDescription, Integer Sequence) {
     this.Number = Number;
     this.Name = Name;
     this.LocalDateTime = LocalDateTime;
     this.ReasonDescription = ReasonDescription;
     this.Sequence = Sequence;
    }

    public RowInfo(String number, String name, String localDateTime, String reasonDescription) {
        Number = number;
        Name = name;
        LocalDateTime = localDateTime;
        ReasonDescription = reasonDescription;
    }



    public RowInfo() {
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
