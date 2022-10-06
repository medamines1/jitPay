package com.user.jitpay.user.management.dto;

import lombok.Data;

@Data
public class SaveUpdateUser {

    private String userId;

    private String email;

    private String firstName;

    private String secondName;
}
