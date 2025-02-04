package com.zacharia.applicatiofybe.dto;

import com.zacharia.applicatiofybe.enums.JobStatusEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class JobApplicationDetailRequestDTO {
    private Long id;
    @NotBlank(message ="Job title is required")
    private String jobTitle;
    @NotBlank(message ="Company name is required")
    private String companyName;
    //@NotNull(message = "Application date is required")
    private LocalDate appliedDate;
    private LocalDate interviewDate;
    private String applicationMethod;
    //@NotNull(message = "Status is required")
    private JobStatusEnum status;
    private String applicationLink;
    private String recruiterName;
    private String recruiterContact;
    private String notes;
}
