package com.example.jmsproducer;

import org.springframework.jms.core.ProducerCallback;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

/**
 * Created by jason-geng on 5/14/17.
 */
public class HelloSessionCallback implements ProducerCallback {

    @Override
    public Object doInJms(Session session, MessageProducer messageProducer) throws JMSException {
        messageProducer.send(session.createTextMessage("xxxx"));
        return "callback";
    }
}
