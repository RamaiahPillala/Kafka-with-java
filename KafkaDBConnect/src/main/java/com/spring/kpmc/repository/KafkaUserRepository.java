package com.spring.kpmc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.kpmc.entity.KafkaUser;

@Repository
public interface KafkaUserRepository extends CrudRepository<KafkaUser, String>{

//public interface KafkaUserRepository extends JpaRepository<KafkaUser, String>{
	
}
