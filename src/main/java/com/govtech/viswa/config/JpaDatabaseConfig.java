package com.govtech.viswa.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.govtech.viswa.jparepo")  //, entityManagerFactoryRef="emf"
public class JpaDatabaseConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean("dataSource")
    @Primary
    public DataSource dataSource(){

        return DataSourceBuilder
                .create()
                .build();
    }
}
