package com.govtech.viswa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author Sarav on 10 Aug 2022
 * @project govtech
 * @package com.govtech.viswa
 * @class ViswaApplication
 */

@SpringBootApplication
//@Configuration
//@EnableAutoConfiguration(exclude = {WebMvcAutoConfiguration.class })
//@EnableAutoConfiguration
//@EnableR2dbcRepositories("com.govtech.viswa.repo")
//@EnableJpaRepositories(basePackages = "com.govtech.viswa.jparepo")  //, entityManagerFactoryRef="emf"
//@EnableWebFlux
public class ViswaApplication  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ViswaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}

/*
	@Autowired
	DataSource dataSource;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		//JpaVendorAdapteradapter can be autowired as well if it's configured in application properties.
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(false);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		//Add package to scan for entities.
		factory.setPackagesToScan("com.govtech.viswa.entity");
		factory.setDataSource(dataSource);
		return factory;
	}
*/
}
