package com.example.jmsproducer;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

/**
 * MQ 生产者 - 点对点模式
 * Created by jason-geng on 5/21/17.
 */
@SpringBootApplication
@EnableJms
public class Application {

    @Value("${jms.broker-url}")
    private String jmsBrokerUrl;

    @Value("${jms.user}")
    private String jmsUser;

    @Value("${jms.password}")
    private String jmsPassword;

//    @Autowired
//    private AtomikosJtaConfiguration jtaConfiguration ;

    @Bean
    public ConnectionFactory connectionFactory(){
        //支持XA
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(jmsBrokerUrl);
        connectionFactory.setUserName(jmsUser);
        connectionFactory.setPassword(jmsPassword);
        return connectionFactory;
    }

    @Bean
    public JmsTemplate jmsQueueTemplate(){
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
        return jmsTemplate;
    }

    @Bean
    public JmsTemplate jmsTopicTemplate(){
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
        jmsTemplate.setPubSubDomain(true);
        return jmsTemplate;
    }

    /**
     * 分布式事务
     */
    @Bean
    public JmsTransactionManager jmsTransactionManager(){
//        JtaTransactionManager transactionManager = new JtaTransactionManager();
//        new UserTransactionAdapter();
        return new JmsTransactionManager(connectionFactory());
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
