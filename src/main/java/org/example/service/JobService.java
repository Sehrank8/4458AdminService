package org.example.service;

import org.example.model.Job;
import org.example.repository.JobRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class JobService {
    private final JobRepository repo;
    private final RabbitTemplate rabbitTemplate;

    @Value("${job.queue.name}")
    private String queueName;

    public JobService(JobRepository repo, RabbitTemplate rabbitTemplate) {
        this.repo = repo;
        this.rabbitTemplate = rabbitTemplate;
    }

    public Job saveJob(Job job) {
        Job saved = repo.save(job);
        rabbitTemplate.convertAndSend(queueName, saved);
        return saved;
    }

    public Optional<Job> updateJob(String id, Job updatedJob) {
        return repo.findById(id).map(existingJob -> {
            existingJob.setTitle(updatedJob.getTitle());
            existingJob.setDescription(updatedJob.getDescription());
            existingJob.setCity(updatedJob.getCity());
            existingJob.setCompany(updatedJob.getCompany());
            existingJob.setType(updatedJob.getType());
            existingJob.setLastUpdated(LocalDateTime.now());
            return repo.save(existingJob);
        });
    }
    public void deleteJob(String id) {
        repo.deleteById(id);
    }
}
