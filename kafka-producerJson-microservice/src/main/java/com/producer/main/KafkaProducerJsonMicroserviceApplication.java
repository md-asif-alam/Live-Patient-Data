package com.producer.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KafkaProducerJsonMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerJsonMicroserviceApplication.class, args);
	}

}
