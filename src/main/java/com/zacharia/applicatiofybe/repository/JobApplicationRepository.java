package com.zacharia.applicatiofybe.repository;

import com.zacharia.applicatiofybe.entity.JobApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplicationEntity,Long> {
}
