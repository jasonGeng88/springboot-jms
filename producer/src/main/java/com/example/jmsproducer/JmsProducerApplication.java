package com.example.jmsproducer;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
@EnableJms
public class JmsProducerApplication {

	@Bean
	public JmsTemplate jmsTemplate(){
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL("tcp://127.0.0.1:61616");
		connectionFactory.setUserName("admin");
		connectionFactory.setPassword("admin");
		return new JmsTemplate(connectionFactory);
	}


	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(JmsProducerApplication.class, args);
		HelloProducer producer = context.getBean(HelloProducer.class);
		producer.send();

//		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

		// Send a message with a POJO - the template reuse the message converter
//		System.out.println("Sending an email message.");
//		jmsTemplate.convertAndSend("mailbox", "hello world!");
	}
}
