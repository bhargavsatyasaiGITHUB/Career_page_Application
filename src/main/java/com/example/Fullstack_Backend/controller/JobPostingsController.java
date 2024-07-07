package com.example.Fullstack_Backend.controller;

import com.example.Fullstack_Backend.model.JobPosting;
import com.example.Fullstack_Backend.service.JobPostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobPosting")
public class JobPostingsController {
    @Autowired
    private JobPostingService jobPostingService;

    @GetMapping
    public List<JobPosting> getAllJobPostings() {
        return jobPostingService.getAllJobPostings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobPosting> getJobPostingById(@PathVariable Long id) {
        return jobPostingService.getJobPostingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public JobPosting createJobPosting(@RequestBody JobPosting jobPosting) {
        return jobPostingService.createJobPosting(jobPosting);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobPosting> updateJobPosting(@PathVariable Long id, @RequestBody JobPosting jobPostingDetails) {
        return ResponseEntity.ok(jobPostingService.updateJobPosting(id, jobPostingDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobPosting(@PathVariable Long id) {
        jobPostingService.deleteJobPosting(id);
        return ResponseEntity.noContent().build();
    }
}
