package com.example.jmsproducer;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.apache.activemq.ActiveMQXAConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jta.atomikos.AtomikosXAConnectionFactoryWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.jms.ConnectionFactory;
import javax.transaction.SystemException;

/**
 * MQ 生产者 - 点对点模式
 * Created by jason-geng on 5/21/17.
 */
@SpringBootApplication
@EnableJms
public class XaApplication {

    @Value("${jms.broker-url}")
    private String jmsBrokerUrl;

    @Value("${jms.user}")
    private String jmsUser;

    @Value("${jms.password}")
    private String jmsPassword;

    @Bean
    public ActiveMQXAConnectionFactory xaFactory(){
        //支持XA
        ActiveMQXAConnectionFactory factory = new ActiveMQXAConnectionFactory();
        factory.setBrokerURL(jmsBrokerUrl);
        factory.setUserName(jmsUser);
        factory.setPassword(jmsPassword);
        return factory;
    }

    public ConnectionFactory connectionFactory(){

        AtomikosXAConnectionFactoryWrapper wrapper = new AtomikosXAConnectionFactoryWrapper();
        return wrapper.wrapConnectionFactory(xaFactory());
    }

    @Bean
    public JmsTemplate jmsQueueTemplate(){
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
        jmsTemplate.setSessionTransacted(true);
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
    public JtaTransactionManager jmsTransactionManager() throws SystemException {
        UserTransactionImp userTransactionImp = new UserTransactionImp();
        userTransactionImp.setTransactionTimeout(300);

        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(false);

        return new JtaTransactionManager(userTransactionImp, userTransactionManager);
    }

    public static void main(String[] args) {
        SpringApplication.run(XaApplication.class, args);
    }

}
