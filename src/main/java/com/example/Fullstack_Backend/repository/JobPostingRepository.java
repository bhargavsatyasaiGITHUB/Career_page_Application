package com.example.Fullstack_Backend.repository;

import com.example.Fullstack_Backend.model.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {

}
