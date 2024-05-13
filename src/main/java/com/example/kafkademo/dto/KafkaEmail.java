package com.example.kafkademo.dto;


import java.util.List;

public class KafkaEmail {
    public EmailTemplate EmailTemplateKey;
    public ApproveStatus ApproveStatus;
    public List<Person> Persons;
    public int Sequence;
    public String ReferenceNo;
    public String Link;
    public String CompanyName;
    public List<Process> Process;

    @Override
    public String toString() {
        return "KafkaEmail{" +
                "EmailTemplateKey=" + EmailTemplateKey +
                ", ApproveStatus=" + ApproveStatus +
                ", Persons=" + Persons +
                ", Sequence=" + Sequence +
                ", ReferenceNo='" + ReferenceNo + '\'' +
                ", Link='" + Link + '\'' +
                ", CompanyName='" + CompanyName + '\'' +
                ", Process=" + Process +
                '}';
    }
}
