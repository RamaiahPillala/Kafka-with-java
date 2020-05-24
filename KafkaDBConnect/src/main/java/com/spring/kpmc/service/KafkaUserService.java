package com.spring.kpmc.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.kpmc.entity.KafkaUser;
import com.spring.kpmc.repository.KafkaUserRepository;
 
@Service
public class KafkaUserService {
	
	@Autowired	
	KafkaUserRepository repository;

	// getting data from DB using findAll() method
	
	public Collection<? extends KafkaUser> getAllUsers() {
     List<KafkaUser> userList = new ArrayList<>();
     repository.findAll().forEach(userList::add);
		return userList;
	}
  
	public Collection<? extends KafkaUser> getUserById(String userId) {
    
		List<KafkaUser> userInfo = new ArrayList<>();
		userInfo.add(repository.findById(userId).get());
		
		return userInfo;
	}

	public KafkaUser save(KafkaUser user) {
		return repository.save(user);
		
	}

}
