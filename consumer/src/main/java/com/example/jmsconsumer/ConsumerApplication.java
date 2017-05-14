package com.example.jmsconsumer;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@SpringBootApplication
@EnableJms
public class ConsumerApplication {

	@Bean
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
		DefaultJmsListenerContainerFactory factory =
				new DefaultJmsListenerContainerFactory();
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL("tcp://127.0.0.1:61616");
		connectionFactory.setUserName("admin");
		connectionFactory.setPassword("admin");
		factory.setConnectionFactory(connectionFactory);
		factory.setConcurrency("3-10");
		return factory;
	}

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}
}
