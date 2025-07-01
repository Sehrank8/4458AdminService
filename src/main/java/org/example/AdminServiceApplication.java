package org.example;

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AdminServiceApplication {

    @Value("${spring.rabbitmq.addresses:NOT_FOUND}")
    private String rabbitUri;

    @Value("${SPRING_RABBITMQ_ADDRESS:NOT_FOUND}")
    private String rabbitUri2;

    public static void main(String[] args) {
        SpringApplication.run(AdminServiceApplication.class, args);
    }

    @Bean
    public Queue jobQueue() {
        return new Queue("jobQueue", true);
    }
    @PostConstruct
    public void logRabbitUri() {
        System.out.println(">>>> Loaded RabbitMQ URI: " + rabbitUri);
    }
    @PostConstruct
    public void logRabbitUri2() {
        System.out.println(">>>> Loaded RabbitMQ URI: " + rabbitUri2);
    }
}
