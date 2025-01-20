package com.zacharia.applicatiofybe.controller;

import com.zacharia.applicatiofybe.dto.JobApplicationRequestDTO;
import com.zacharia.applicatiofybe.entity.JobApplicationEntity;
import com.zacharia.applicatiofybe.entity.JobStatusEntity;
import com.zacharia.applicatiofybe.service.JobApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/application")

public class JobApplicationController {

    private final JobApplicationService jobApplicationService;
    public JobApplicationController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }


    @PostMapping("/create")
    public ResponseEntity<JobApplicationEntity> createJobApplication(@RequestBody @Valid JobApplicationRequestDTO jobApplicationRequestDTO, Principal principal) {
        System.out.println("Inside createJobApplication");
        JobApplicationEntity jobApplicationEntity = jobApplicationService.createJobApplication(principal.getName(), jobApplicationRequestDTO) ;
        return ResponseEntity.ok(jobApplicationEntity);
    }

    @GetMapping("/all-job-application")
    public List<JobApplicationEntity> getAllJobApplication(Principal principal){
        return this.jobApplicationService.getAllJobApplications(principal.getName());
    }

}
