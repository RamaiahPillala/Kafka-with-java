package com.spring.kpmc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableAutoConfiguration
@Configuration
@RestController
@ComponentScan
public class KafkaFileEventsApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaFileEventsApplication.class, args);
	}

}
