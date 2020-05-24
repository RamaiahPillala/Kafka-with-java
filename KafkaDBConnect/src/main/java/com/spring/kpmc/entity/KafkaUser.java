package com.spring.kpmc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="KafkaUser")

public class KafkaUser {
	@Id
	@Column(name="UserId")	
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String userId;
	@Column(name="Username")
	private String username;	
    @Column(name="Password")
	private String password;
	@Column(name ="Age")
	private int age;
	@Column(name ="Gender")
	private char gender;
	
	public KafkaUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public KafkaUser(String userId, String username, String password, int age, char gender) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.age = age;
		this.gender = gender;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}
	

}
