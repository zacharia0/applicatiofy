package com.zacharia.applicatiofybe.service;

import com.zacharia.applicatiofybe.dto.JobApplicationDetailRequestDTO;
import com.zacharia.applicatiofybe.dto.JobApplicationRequestDTO;
import com.zacharia.applicatiofybe.dto.UpdateJobApplicationRequestDTO;
import com.zacharia.applicatiofybe.entity.AccountEntity;
import com.zacharia.applicatiofybe.entity.JobApplicationEntity;
import com.zacharia.applicatiofybe.enums.JobStatusEnum;
import com.zacharia.applicatiofybe.repository.AccountRepository;
import com.zacharia.applicatiofybe.repository.JobApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

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
        jobApplicationEntity.setAppliedDate(jobApplicationRequestDTO.getAppliedDate());
        jobApplicationEntity.setDateApplied(jobApplicationRequestDTO.getAppliedDate());
        jobApplicationEntity.setInterviewDate(jobApplicationRequestDTO.getInterviewDate());
        jobApplicationEntity.setApplicationMethod(jobApplicationRequestDTO.getApplicationMethod());
        jobApplicationEntity.setStatus(jobApplicationRequestDTO.getStatus());
        jobApplicationEntity.setApplicationLink(jobApplicationRequestDTO.getApplicationLink());
        jobApplicationEntity.setRecruiterName(jobApplicationRequestDTO.getRecruiterName());
        jobApplicationEntity.setRecruiterContact(jobApplicationRequestDTO.getRecruiterContact());
        jobApplicationEntity.setNotes(jobApplicationRequestDTO.getNotes());

        return this.jobApplicationRepository.save(jobApplicationEntity);
    }

    public List<JobApplicationEntity> getAllJobApplications(String username) {
        AccountEntity account = this.accountRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        return this.jobApplicationRepository.findByAccountId(account.getId());

    }

    public JobApplicationDetailRequestDTO getJobApplicationById(Long jobId) {
        JobApplicationEntity entity = jobApplicationRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job application not found with ID: " + jobId));

        return new JobApplicationDetailRequestDTO(entity.getId(),entity.getJobTitle(),entity.getCompanyName(),entity.getAppliedDate(),entity.getInterviewDate(),entity.getApplicationMethod(),entity.getStatus(),entity.getApplicationLink(),entity.getRecruiterName(),entity.getRecruiterContact(),entity.getNotes());
    }


    public UpdateJobApplicationRequestDTO updateJobApplication(Long jobId, UpdateJobApplicationRequestDTO updateJobApplicationRequestDTO) {
        JobApplicationEntity jobApplicationEntity = this.jobApplicationRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job application not found"));
        jobApplicationEntity.setJobTitle(updateJobApplicationRequestDTO.getJobTitle());
        jobApplicationEntity.setCompanyName(updateJobApplicationRequestDTO.getCompanyName());
        jobApplicationEntity.setAppliedDate(updateJobApplicationRequestDTO.getAppliedDate());
        jobApplicationEntity.setDateApplied(updateJobApplicationRequestDTO.getAppliedDate());
        jobApplicationEntity.setInterviewDate(updateJobApplicationRequestDTO.getInterviewDate());
        jobApplicationEntity.setApplicationMethod(updateJobApplicationRequestDTO.getApplicationMethod());
        jobApplicationEntity.setStatus(updateJobApplicationRequestDTO.getStatus());
        jobApplicationEntity.setApplicationLink(updateJobApplicationRequestDTO.getApplicationLink());
        jobApplicationEntity.setRecruiterName(updateJobApplicationRequestDTO.getRecruiterName());
        jobApplicationEntity.setRecruiterContact(updateJobApplicationRequestDTO.getRecruiterContact());
        jobApplicationEntity.setNotes(updateJobApplicationRequestDTO.getNotes());
        jobApplicationRepository.save(jobApplicationEntity);

        return new UpdateJobApplicationRequestDTO(
                updateJobApplicationRequestDTO.getJobTitle(),
                updateJobApplicationRequestDTO.getCompanyName(),
                updateJobApplicationRequestDTO.getAppliedDate(),
                updateJobApplicationRequestDTO.getInterviewDate(),
                updateJobApplicationRequestDTO.getApplicationMethod(),
                updateJobApplicationRequestDTO.getStatus(),
                updateJobApplicationRequestDTO.getApplicationLink(),
                updateJobApplicationRequestDTO.getRecruiterName(),
                updateJobApplicationRequestDTO.getRecruiterContact(),
                updateJobApplicationRequestDTO.getNotes()
        );

    }


    public UpdateJobApplicationRequestDTO deleteJobApplication(Long jobId) {
        JobApplicationEntity jobApplicationEntity = jobApplicationRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job application not found"));
          jobApplicationRepository.delete(jobApplicationEntity);

          return new UpdateJobApplicationRequestDTO(jobApplicationEntity.getJobTitle(),jobApplicationEntity.getCompanyName(),jobApplicationEntity.getAppliedDate(),jobApplicationEntity.getInterviewDate(),jobApplicationEntity.getApplicationMethod(),jobApplicationEntity.getStatus(),jobApplicationEntity.getApplicationLink(),jobApplicationEntity.getRecruiterName(),jobApplicationEntity.getRecruiterContact(),jobApplicationEntity.getNotes());
    }



    public Map<JobStatusEnum, Long> getJobStatusCount(String username) {

        AccountEntity account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Account not found"));


        Map<JobStatusEnum, Long> statusCounts = new EnumMap<>(JobStatusEnum.class);

        // Count job applications for each status
        for(JobStatusEnum status: JobStatusEnum.values()){
            long count = jobApplicationRepository.countByStatusAndAccountUsername(status, account.getUsername());
            statusCounts.put(status,count);
        }
        return statusCounts;
    }



}
