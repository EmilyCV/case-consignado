package br.com.consigned.userverificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class UserVerificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserVerificationServiceApplication.class, args);
	}

}