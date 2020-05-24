package com.spring.kpmc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class KafkaDbConnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaDbConnectApplication.class, args);
	}

}
