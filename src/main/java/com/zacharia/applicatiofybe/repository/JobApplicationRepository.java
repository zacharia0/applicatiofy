package com.zacharia.applicatiofybe.repository;

import com.zacharia.applicatiofybe.entity.JobApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplicationEntity,Long> {
    List<JobApplicationEntity> findByAccountId(Long userId);
}
