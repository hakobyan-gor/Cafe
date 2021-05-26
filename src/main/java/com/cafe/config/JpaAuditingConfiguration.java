package com.cafe.config;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.context.annotation.Bean;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfiguration {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }

}