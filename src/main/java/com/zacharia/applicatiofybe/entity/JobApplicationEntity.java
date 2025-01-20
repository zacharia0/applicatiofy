package com.zacharia.applicatiofybe.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class JobApplicationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String jobTitle;
    @Column(nullable = false)
    private String companyName;
    @Column
    private LocalDate applicationDate;
    @Column
    private LocalDate dateApplied;
    @Column
    private LocalDate interviewDate;
    @Column
    private String applicationMethod; // How the application was submitted(e.g. Online portal, email, Recruiter)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobStatusEntity status;

    @Column
    private String applicationLink;
    @Column
    private String recruiterName;
    @Column
    private String recruiterContact;

    @Column
    private String notes;


    @JsonIgnoreProperties("jobApplication") // Avoid infinite recursion by ignoring the 'jobApplication' field in AccountEntity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private AccountEntity account;


}
