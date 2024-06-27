package com.employees;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.employees.common.config.JwtProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class)
public class EmpcreatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpcreatorApplication.class, args);
	}

}
