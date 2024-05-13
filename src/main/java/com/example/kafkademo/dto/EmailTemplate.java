package com.example.kafkademo.dto;

public enum EmailTemplate {
    Verification("Verification"),
    RegistrationPendingForApproval("RegistrationPendingForApproval"),
    RequestPending("RequestPending"),
    RequestApproved("RequestApproved"),
    RequestRejected("RequestRejected"),
    RequestHeld("RequestHeld"),
    BidPending("BidPending"),
    BidApproved("BidApproved"),
    BidRejected("BidRejected"),
    BidHeld("BidHeld"),
    UserPending("UserPending"),
    UserApproved("UserApproved"),
    UserRejected("UserRejected"),
    UserHeld("UserHeld"),
    RegistrationIsPending("RegistrationIsPending"),
    RegistrationIsPendingForAdmin("RegistrationIsPendingForAdmin"),
    RegistrationIsApproved("RegistrationIsApproved"),
    RegistrationIsRejected("RegistrationIsRejected"),
    EmailConfirmPage("EmailConfirmPage"),
    EmailVerification("EmailVerification"),
    EvaluationForCompanyUser("EvaluationForCompanyUser"),
    EvaluationForVendor("EvaluationForVendor");

    private final String value;

    EmailTemplate(String value) {
        this.value = value;
    }
}
