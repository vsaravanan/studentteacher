package com.govtech.viswa.config;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableR2dbcRepositories("com.govtech.viswa.repo")
@EnableTransactionManagement
public class DatabaseConfig { // extends AbstractR2dbcConfiguration {

/*
    @Autowired
    Environment env;

    @Override
    @Bean
    public ConnectionFactory connectionFactory() {

        // postgres
        return ConnectionFactories.get (
                ConnectionFactoryOptions.builder()
                        .option(DRIVER, env.getProperty("spring.datasource.driver-class-name") )
                        .option(HOST, env.getProperty("spring.datasource.host") )
                        .option(PORT, Integer.valueOf(env.getProperty("spring.datasource.port")) )
                        .option(DATABASE, env.getProperty("spring.datasource.database") )
                        .option(USER, env.getProperty("spring.r2dbc.username") )
                        .option(PASSWORD, env.getProperty("spring.r2dbc.password") )
                        .build()
        );
    }

    @Bean
    DatabaseClient databaseClient(ConnectionFactory connectionFactory) {
        return DatabaseClient.builder()
                .connectionFactory(connectionFactory)
                //.bindMarkers(() -> BindMarkersFactory.named(":", "", 20).create())
                .namedParameters(true)
                .build();
    }
*/
    @Bean
    public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {

        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);

        CompositeDatabasePopulator populator = new CompositeDatabasePopulator();
        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("droptables.sql")));
        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("schema-postgres.sql")));
        initializer.setDatabasePopulator(populator);

        return initializer;
    }

    @Bean
    ReactiveTransactionManager transactionManager(ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }
}
