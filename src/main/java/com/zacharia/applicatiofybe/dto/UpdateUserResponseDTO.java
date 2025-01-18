package com.zacharia.applicatiofybe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateUserResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
}
