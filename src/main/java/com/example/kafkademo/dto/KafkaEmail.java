package com.example.kafkademo.dto;


import java.util.List;

public class KafkaEmail {
    public EmailTemplate EmailTemplateKey;
    public ApproveStatus ApproveStatus;
    public List<Person> Persons;
    public String ReferenceNo;
    public String Link;
    public String CompanyName;
    public List<RowInfo> rowInfos;

    @Override
    public String toString() {
        return "KafkaEmail{" +
                "EmailTemplateKey=" + EmailTemplateKey +
                ", ApproveStatus=" + ApproveStatus +
                ", Persons=" + Persons +
                ", ReferenceNo='" + ReferenceNo + '\'' +
                ", Link='" + Link + '\'' +
                ", CompanyName='" + CompanyName + '\'' +
                ", RowInfos=" + rowInfos +
                '}';
    }
}
