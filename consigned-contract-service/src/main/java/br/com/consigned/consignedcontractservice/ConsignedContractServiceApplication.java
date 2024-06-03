package br.com.consigned.consignedcontractservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
@EnableJpaRepositories
public class ConsignedContractServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsignedContractServiceApplication.class, args);
	}

}
