package com.leo.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JPAConfig {

    @Bean
    public AuditorAware<?> auditorAware() {

        //从Session获取用户信息

        return () -> Optional.of("Unknown").get();
    }
}
