package com.employee.management.Validate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
* ValidateApplication is for insert and calculate employee in database.
*/
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.employee.management*")
public class ValidateApplication {

	public static void main(String[] args) {
		SpringApplication.run(ValidateApplication.class, args);
	}
}
