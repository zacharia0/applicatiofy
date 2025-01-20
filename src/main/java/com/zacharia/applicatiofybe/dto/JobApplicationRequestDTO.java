package com.zacharia.applicatiofybe.dto;

import com.zacharia.applicatiofybe.entity.AccountEntity;
import com.zacharia.applicatiofybe.entity.JobStatusEntity;
import jakarta.validation.constraints.NotBlank;
//import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
//@AllArgsConstructor
public class JobApplicationRequestDTO {
    @NotBlank(message ="Job title is required")
    private String jobTitle;
    @NotBlank(message ="Company name is required")
    private String companyName;
//    @NotNull(message = "Application date is required")
    private LocalDate applicationDate;
    private LocalDate interviewDate;
    private String applicationMethod;
//    @NotNull(message = "Status is required")
    private JobStatusEntity status;
    private String applicationLink;
    private String recruiterName;
    private String recruiterContact;
    private String notes;
    private AccountEntity account;;


}
