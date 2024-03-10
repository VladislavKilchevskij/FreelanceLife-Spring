package org.example.config;

import org.example.repository.FreelancerRepository;
import org.example.repository.OrderRepository;
import org.example.repository.QualificationRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.*;

@Configuration
public class DatasourceTestConfig {

    @Bean
    public FreelancerRepository freelancerRepository() {
        return mock(FreelancerRepository.class);
    }

    @Bean
    public OrderRepository orderRepository() {
        return mock(OrderRepository.class);
    }

    @Bean
    public QualificationRepository qualificationRepository() {
        return mock(QualificationRepository.class);
    }


}