package com.example.kafkademo.dto;

public enum EmailTemplate {
    Verification,  //verification Email
    RegistrationPendingForApproval, //Registration Pending For Approval for single user
    RequestPending, //Request is pending
    RequestApproved, //Request is approved
    RequestRejected, //Request is rejected
    RequestHeld, //Request is held
    UserPending, //User is pending
    UserApproved, //User Approved
    UserRejected, //User Reject
    UserHeld //User Held
}
