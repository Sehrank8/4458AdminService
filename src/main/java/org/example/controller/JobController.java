package org.example.controller;

import org.example.model.Job;
import org.example.service.JobService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/jobs")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public Job createJob(@RequestBody Job job) {
        return jobService.saveJob(job);
    }

    @PutMapping("/{id}")
    public Job updateJob(@PathVariable String id, @RequestBody Job updatedJob) {
        return jobService.updateJob(id, updatedJob)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + id));
    }
}
