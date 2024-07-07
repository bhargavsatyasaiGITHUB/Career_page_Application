package com.example.Fullstack_Backend.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "Job_Postings")
public class JobPosting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private Long jobId;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "location", length = 100)
    private String location;

    @Column(name = "years_of_experience", length = 50)
    private String yearsOfExperience;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, columnDefinition = "ENUM('HIRING', 'CLOSED') DEFAULT 'HIRING'")
    private JobStatus status = JobStatus.HIRING;


    @OneToOne()
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "posted_at", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @org.hibernate.annotations.CreationTimestamp
    private LocalDateTime postedAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @org.hibernate.annotations.UpdateTimestamp
    private LocalDateTime updatedAt;

    public enum JobStatus {
        HIRING,
        CLOSED
    }
    // Constructors, getters, and setters

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setYearsOfExperience(String yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setPostedAt(LocalDateTime postedAt) {
        this.postedAt = postedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
