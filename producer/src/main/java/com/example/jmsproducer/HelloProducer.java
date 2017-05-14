package com.example.jmsproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by jason-geng on 5/14/17.
 */
@Component
public class HelloProducer {

    @Autowired
    private JmsTemplate jmsTemplate;


    public void send(){
        //send
        jmsTemplate.convertAndSend("hello", "hello world");

        //callback
        String res = (String) jmsTemplate.execute("hello", new HelloSessionCallback());
        System.out.println(res);
    }
}
