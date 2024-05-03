package com.example.kafkademo.dto;

public enum ApproveStatus {
    Approved (1),
    Rejected (2),
    Hold (3);

    public final int value;

    ApproveStatus(int value) {
        this.value = value;
    }
}
