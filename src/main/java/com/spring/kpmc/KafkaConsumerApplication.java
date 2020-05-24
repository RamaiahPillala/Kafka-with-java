package com.spring.kpmc;

import java.util.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@ComponentScan
public class KafkaConsumerApplication {

	List<String> messages = new ArrayList<>();

	 User userFromTopic = null;

	// End point which we can use in down streams
	
	@GetMapping("/consumeStringMsg")
	public List<String> consumeMsg() {
		return messages;
	}
    
	@GetMapping("/consumeJsonMsg")
	public User consumeJsonObjMsg() {
		return userFromTopic;
	}

	// Listener method to talk to kafka topic or getting raw data from Topic( for
	// plain text)
	@KafkaListener(groupId = "groupId1", topics = "kafkatopic1", containerFactory = "kafkaListenerContainerFactory")
	public List<String> getMsgFromTopic(String data) {
		messages.add(data);
		return messages;

	}

	// Listener method to talk to kafka topic or getting raw data from Topic(for
	// Json object)
	@KafkaListener(groupId = "groupId2", topics = "kafkatopic1", containerFactory = "userKafkaListenerContainerFactory")
	public User getJsonFromTopic(User user) {
		userFromTopic = user;
		return userFromTopic;

	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerApplication.class, args);
	}

}
