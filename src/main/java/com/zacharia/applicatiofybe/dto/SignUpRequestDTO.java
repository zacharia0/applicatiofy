package com.zacharia.applicatiofybe.dto;

import lombok.Data;

@Data
public class SignUpRequestDTO {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
}
