package com.spring.kpmc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.kpmc.entity.KafkaUser;
import com.spring.kpmc.exception.ResourceNotFoundException;
import com.spring.kpmc.repository.KafkaUserRepository;
import com.spring.kpmc.service.KafkaUserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/user")
public class KafkaUserController {

	@Autowired(required = true)
	KafkaTemplate<String, Object> template;
	
	private String topic = "kafkatopic1";
	private String topic3 = "kafkatopic3";

	@Autowired
	KafkaUserService userService;

	@Autowired
	KafkaUserRepository repository;

	/*
	 * // This method returns all the instances of KafkaUser types
	 */
	@GetMapping(path = "/getAllUsers")
	public List<KafkaUser> getAllUsers() {
		List<KafkaUser> userList = new ArrayList<>();
		userList.addAll(userService.getAllUsers());
		// below line is addinng data to kafka topic only
		/*
		 * for (int i = 0; i < userList.size(); i++) { KafkaUser u = userList.get(i);
		 * template.send(topic, "userID: " + u.getUserId() + " Username :" +
		 * u.getUsername() + "  Password :" + u.getPassword() + "  Age :" + u.getAge() +
		 * "  Gender :" + u.getGender()); }
		 */
		template.send(topic,userList);
		return userList;
	}
	/*
	 * this method returns required user information by using ID
	 */

	@GetMapping(path = "/getUserById/{userId}")
	public List<KafkaUser> getUserByID(@PathVariable(name = "userId") String userId) {
		List<KafkaUser> userInfo = new ArrayList<>();
		userInfo.addAll(userService.getUserById(userId));
		template.send(topic3,userInfo );
		return userInfo;

	}

	@PostMapping(path = "/userRegister")
	public KafkaUser createEmployee(@RequestBody KafkaUser user) {
		template.send(topic3,user );
		return userService.save(user);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<KafkaUser> updateUser(@PathVariable(value = "id") String userId,
			@Valid @RequestBody KafkaUser userDetails) throws ResourceNotFoundException {
		KafkaUser user = repository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + userId));

		user.setUserId(userDetails.getUserId());
		user.setAge(userDetails.getAge());
		user.setGender(userDetails.getGender());
		user.setPassword(userDetails.getPassword());
		user.setUsername(userDetails.getUsername());

		final KafkaUser updateUser = repository.save(user);
		template.send(topic3,updateUser );
		return ResponseEntity.ok(updateUser);
	}

	@DeleteMapping("/delete/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") String userId) throws ResourceNotFoundException {
		KafkaUser user = repository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

		repository.delete(user);
		template.send(topic3,user );
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@Bean
	public ProducerFactory<String, Object> producerFactory() {
		Map<String, Object> configs = new HashMap<>();
		configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return new DefaultKafkaProducerFactory<String, Object>(configs);

	}
   @Bean
	public KafkaTemplate<String, Object> kafkaTemplate() {
		return new KafkaTemplate<String, Object>(producerFactory());
	}

}
