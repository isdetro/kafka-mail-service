package com.example.kafkademo.utils;

public class EmailUtils {
    public static final String NEW_USER_ACCOUNT_VERIFICATION = "New User Account Verification";
    public static final String UTF_8_ENCODING = "UTF-8";


    public static String getVerificationEmail(String name, String host, String token) {
        return "Hello " + name + """
                , \n Your new account has been created.Please click the link below to verify your account \n
                """ + getVerificationUrl(host, token) + "\n\n The Support Team";
    }

    public static String getSimpleMessage(String name) {
        return name;
    }



    public static String getVerificationUrl(String host, String token) {
        return host + "/api/user?token=" + token;
    }
}
