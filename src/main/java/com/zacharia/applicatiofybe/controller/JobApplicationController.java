package com.zacharia.applicatiofybe.controller;

import com.zacharia.applicatiofybe.dto.JobApplicationDetailRequestDTO;
import com.zacharia.applicatiofybe.dto.JobApplicationRequestDTO;
import com.zacharia.applicatiofybe.dto.UpdateJobApplicationRequestDTO;
import com.zacharia.applicatiofybe.entity.JobApplicationEntity;
import com.zacharia.applicatiofybe.enums.JobStatusEnum;
import com.zacharia.applicatiofybe.service.JobApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

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

    @PutMapping("/update/{jobId}")
    public ResponseEntity<UpdateJobApplicationRequestDTO> updateJobApplication(@PathVariable Long jobId, @RequestBody @Valid UpdateJobApplicationRequestDTO updateJobApplicationRequestDTO ) {
        this.jobApplicationService.updateJobApplication(jobId, updateJobApplicationRequestDTO);
        return ResponseEntity.ok(updateJobApplicationRequestDTO);
    }

    @DeleteMapping("/delete/{jobId}")
    public ResponseEntity<UpdateJobApplicationRequestDTO> deleteJobApplication(@PathVariable Long jobId) {

        return ResponseEntity.ok(this.jobApplicationService.deleteJobApplication(jobId));
    }

    @GetMapping("/singleJob/{jobId}")
    public ResponseEntity<JobApplicationDetailRequestDTO> getJobApplication(@PathVariable Long jobId) {
        System.out.println("Inside getJobApplication" + jobId);
       return  ResponseEntity.ok(jobApplicationService.getJobApplicationById(jobId));

    }

    @GetMapping("/jobStatus")
    public ResponseEntity<Map<JobStatusEnum, Long>> getJobStatusCount(Principal principal) {
        Map<JobStatusEnum, Long> statusCount = jobApplicationService.getJobStatusCount(principal.getName());
        return ResponseEntity.ok(statusCount);
    }
}
