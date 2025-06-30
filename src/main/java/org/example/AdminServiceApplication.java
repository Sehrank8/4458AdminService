package org.example;

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AdminServiceApplication {

    @Value("${spring.rabbitmq.virtual-host:NOT_FOUND}")
    private String virtualHost;

    @Value("${spring.rabbitmq.username:NOT_FOUND}")
    private String rabbitUser;
    public static void main(String[] args) {
        SpringApplication.run(AdminServiceApplication.class, args);
    }

    @Bean
    public Queue jobQueue() {
        return new Queue("jobQueue", true);
    }
    @PostConstruct
    public void logRabbitMQConfig() {
        System.out.println(">>>> Spring loaded RabbitMQ Virtual Host: " + virtualHost);
        System.out.println(">>>> Spring loaded RabbitMQ Username: " + rabbitUser);
    }
}
