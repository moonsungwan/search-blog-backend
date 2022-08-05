package com.task.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BankTaskProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankTaskProjectApplication.class, args);
	}

}
