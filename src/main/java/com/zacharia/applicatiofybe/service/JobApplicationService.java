package com.zacharia.applicatiofybe.service;

import com.zacharia.applicatiofybe.dto.JobApplicationRequestDTO;
import com.zacharia.applicatiofybe.entity.AccountEntity;
import com.zacharia.applicatiofybe.entity.JobApplicationEntity;
import com.zacharia.applicatiofybe.repository.AccountRepository;
import com.zacharia.applicatiofybe.repository.JobApplicationRepository;
import org.springframework.stereotype.Service;

@Service
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final AccountRepository accountRepository;
    public JobApplicationService(JobApplicationRepository jobApplicationRepository, AccountRepository accountRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.accountRepository = accountRepository;
    }

    public JobApplicationEntity createJobApplication(String username, JobApplicationRequestDTO jobApplicationRequestDTO) {
        AccountEntity account = this.accountRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        JobApplicationEntity jobApplicationEntity = new JobApplicationEntity();
        jobApplicationEntity.setAccount(account);
        jobApplicationEntity.setJobTitle(jobApplicationRequestDTO.getJobTitle());
        jobApplicationEntity.setCompanyName(jobApplicationRequestDTO.getCompanyName());
        jobApplicationEntity.setApplicationDate(jobApplicationRequestDTO.getApplicationDate());
        jobApplicationEntity.setDateApplied(jobApplicationRequestDTO.getApplicationDate());
        jobApplicationEntity.setInterviewDate(jobApplicationRequestDTO.getInterviewDate());
        jobApplicationEntity.setApplicationMethod(jobApplicationRequestDTO.getApplicationMethod());
        jobApplicationEntity.setStatus(jobApplicationRequestDTO.getStatus());
        jobApplicationEntity.setApplicationLink(jobApplicationRequestDTO.getApplicationLink());
        jobApplicationEntity.setRecruiterName(jobApplicationRequestDTO.getRecruiterName());
        jobApplicationEntity.setRecruiterContact(jobApplicationRequestDTO.getRecruiterContact());
        jobApplicationEntity.setNotes(jobApplicationRequestDTO.getNotes());

        return this.jobApplicationRepository.save(jobApplicationEntity);
    }


}
