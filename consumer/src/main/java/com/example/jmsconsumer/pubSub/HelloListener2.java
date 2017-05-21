package com.example.jmsconsumer.pubSub;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 发布/订阅模式: 消费者2
 * Created by jason-geng on 5/21/17.
 */
@Component
public class HelloListener2 {

    @JmsListener(destination = "pubSub", containerFactory = "jmsTopicListenerContainerFactory")
    public void receive(String msg){
        System.out.println("订阅者2 - " + msg);
    }
}
