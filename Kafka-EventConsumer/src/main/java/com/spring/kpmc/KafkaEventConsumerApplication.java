package com.spring.kpmc;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan
@RestController
public class KafkaEventConsumerApplication {

	List<String> messages = new ArrayList<>();

	@GetMapping("/consumeFile")
	public List<String> consumeMsg() {
		return messages;
	}

	@KafkaListener(groupId = "groupId3", topics = "kafkatopic3", containerFactory = "kafkaListenerContainerFactory")
	public List<String> getMsgFromTopic(String data) {
		messages.add(data);

		BufferedWriter bf = null;

		try {
			File file = new File("C:\\Files\\downstream\\copy.txt");

			FileWriter fw = new FileWriter(file, true);

			bf = new BufferedWriter(fw);

			Scanner input = new Scanner(data);

			String s = null;

			while (input.hasNextLine()) {

				s = input.nextLine();

				System.out.println("Befor write  :" + s);

				bf.flush();
				bf.write(s);
				bf.newLine();

				System.out.println("After write  :" + s);
			}

		} catch (IOException e) {
			System.out.println(" Error occurred :");
			e.printStackTrace();
		} finally {
			try {
				bf.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return messages;
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaEventConsumerApplication.class, args);
	}

}
