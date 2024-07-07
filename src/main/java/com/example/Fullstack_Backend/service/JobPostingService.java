package com.example.Fullstack_Backend.service;

import com.example.Fullstack_Backend.model.Department;
import com.example.Fullstack_Backend.model.JobPosting;
import com.example.Fullstack_Backend.repository.DepartmentRepository;
import com.example.Fullstack_Backend.repository.JobPostingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JobPostingService {
    @Autowired
    private JobPostingRepository jobPostingRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<JobPosting> getAllJobPostings() {
        return jobPostingRepository.findAll();
    }

    public Optional<JobPosting> getJobPostingById(Long id) {
        return jobPostingRepository.findById(id);
    }

    public JobPosting createJobPosting(JobPosting jobPosting) {

        return jobPostingRepository.save(jobPosting);
    }


    public JobPosting updateJobPosting(Long id, JobPosting jobPostingDetails) {
        JobPosting jobPosting = jobPostingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job Posting not found with id " + id));

        jobPosting.setTitle(jobPostingDetails.getTitle());
        jobPosting.setDescription(jobPostingDetails.getDescription());
        jobPosting.setLocation(jobPostingDetails.getLocation());
        jobPosting.setYearsOfExperience(jobPostingDetails.getYearsOfExperience());
        jobPosting.setStatus(jobPostingDetails.getStatus());
        jobPosting.setDepartment(jobPostingDetails.getDepartment());
        jobPosting.setUpdatedAt(LocalDateTime.now());

        return jobPostingRepository.save(jobPosting);
    }

    public void deleteJobPosting(Long id) {
        JobPosting jobPosting = jobPostingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job Posting not found with id " + id));

        jobPostingRepository.delete(jobPosting);
    }
}
