package com.example.jmsproducer.pubSub;

import com.example.jmsproducer.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * 发布/订阅 - 生产者
 * 1.每个消息可以有多个消费者
 * 2.发布者和订阅者之间有时间上的依赖性。针对某个主题（Topic）的订阅者，它必须创建一个订阅者之后，才能消费发布者的消息
 * 3.为了消费消息，订阅者必须保持运行的状态
 * Created by jason-geng on 5/14/17.
 */
@Component
public class PubSubProducer {

    @Autowired
    private JmsTemplate jmsTopicTemplate;


    public void send(){
        jmsTopicTemplate.send(Constant.TOPIC_NAME, session -> {
            return session.createTextMessage("我是原始消息");
        });
    }

    public void convertAndSend(){
        jmsTopicTemplate.convertAndSend(Constant.TOPIC_NAME, "我是自动转换的消息");
    }
}
