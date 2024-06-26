package br.com.consigned.consignedsimulatorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
@EnableJpaRepositories
public class ConsignedSimulatorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsignedSimulatorServiceApplication.class, args);
	}

}
