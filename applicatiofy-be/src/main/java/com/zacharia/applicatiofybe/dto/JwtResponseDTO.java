package com.zacharia.applicatiofybe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponseDTO {
    private String token;
    private String firstName;
    private String lastName;
    private String username;
    private Long id;


}
