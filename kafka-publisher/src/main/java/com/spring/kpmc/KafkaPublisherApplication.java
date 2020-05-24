package com.spring.kpmc;

import com.spring.kpmc.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class KafkaPublisherApplication {

	@Autowired
	private KafkaTemplate<String, Object> template;
	private String topic = "kafkatopic1";
	
	

	// This is for publishing String message
	@GetMapping("/publish/{name}")
	public String publishMessage(@PathVariable String name) {
		template.send(topic, "Hi " + name + " welcome to KPMC");
		return "Data Published";
	}

	@GetMapping("/publishJson")
	public String publishMessage() {
		User user = new User(1111, "suresh", new String[] { "Chennai", "Telangan", "House NO:97" });
		template.send(topic, user);
		return "Json Data Published";
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaPublisherApplication.class, args);
	}

}
