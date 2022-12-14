package com.govtech.viswa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;


@Configuration
@EnableR2dbcAuditing
public class ReactiveAuditing {

    @Bean
    public MyReactiveAuditorAware myAuditorProvider() {
        return new MyReactiveAuditorAware();
    }
}