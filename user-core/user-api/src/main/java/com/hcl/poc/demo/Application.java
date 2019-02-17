package com.hcl.poc.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.hcl.poc.demo")
@EnableJpaRepositories("com.hcl.poc.demo.dao")
@EntityScan("com.hcl.poc.demo.entity")
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
