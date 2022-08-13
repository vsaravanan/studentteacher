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
public class ViswaApplication  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ViswaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
