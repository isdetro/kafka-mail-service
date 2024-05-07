package com.example.kafkademo.dto;

public enum EmailTemplate {
    Verification("Verification"),
    RegistrationPendingForApproval("RegistrationPendingForApproval"),
    RequestPending("RequestPending"),
    RequestApproved("RequestApproved"),
    RequestRejected("RequestRejected"),
    RequestHeld("RequestHeld"),
    UserPending("UserPending"),
    UserApproved("UserApproved"),
    UserRejected("UserRejected"),
    UserHeld("UserHeld"); ;

    private final String value;

    EmailTemplate(String value) {
        this.value = value;
    }
}
