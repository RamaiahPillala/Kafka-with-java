package com.spring.kpmc;

import com.spring.kpmc.config.*;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Scanner;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
@RestController
@Slf4j
public class KafkaEventPublisherApplication {

	@Autowired
	private KafkaTemplate<String, Object> template = null;
	private String topic = "kafkatopic3";

	static Logger log = org.slf4j.LoggerFactory.getLogger(KafkaEventPublisherApplication.class);
	String data = null;

	@GetMapping("/publishFile")
	public String publishMessage() throws Exception {

		File file = new File("C:\\Files\\source1\\log.txt");
		Date date = new Date();
		Scanner sc = new Scanner(file);

		while (sc.hasNextLine()) {

			data = sc.nextLine();

			// String s = "ramaiah"; System.out.println(s.concat(date.toString()));
			log.info("Up stream File Data :::::::::::::::" + data);
			log.info("Current timestamp :::::::::::::" + date.toString());
			log.info("upstream File Name :::::::::::" + file.getName());
			log.info("upstram File absolute path ::::::::::::" + file.getAbsolutePath());
			log.info("File can be writeble ::::::::::" + file.canWrite());
			log.info("file can be readable::::::::::::" + file.canRead());
			log.info("upstream file size in bytes :::::::" + file.length());
			

			 template.send(topic, data);
		//	System.out.println("data counting   :  " + data);
		}
		data = "upstream File Absolute Path ::::" + data.concat(file.getAbsolutePath()) + "*****" + System.lineSeparator();
		// data = " upstream Upstream file can writeble
		// :::::"+data.concat(file.canWrite())+"******"+ "/n";
		data = "upstream file name :::::::: ::::" + data.concat(file.getName()) + "********" + System.lineSeparator();
		// data = "upstream file lenth in bytes ::::::::"+data.concat(file.length())+
		// "*********" + "/n";
		data = " Current timestamp :::::::::::::::::::" + data.concat(date.toString());

		template.send(topic, data);
		return "File Data Published on :::::"+ "   " +date.toString();
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaEventPublisherApplication.class, args);
	}

}
