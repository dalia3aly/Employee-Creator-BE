package com.employees.empcreator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.employees")
public class EmpcreatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpcreatorApplication.class, args);
	}
}