package com.spring.kpmc.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@Configuration
@EnableKafka
public class KafkaEventConsumerConfig {
	
	// Creating ConsumerFactory for String msgs or plain text
		@Bean
		public ConsumerFactory<String, Object> consumerFactory() {

			Map<String, Object> configs = new HashMap<>();
			configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
			configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
			configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
			configs.put(ConsumerConfig.GROUP_ID_CONFIG, "groupId1");

			return new DefaultKafkaConsumerFactory<>(configs);
		}

		// inserting ConsumerFactory into listener container
		
		@Bean
		public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
			ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
			factory.setConsumerFactory(consumerFactory());
			return factory;

		}


}
