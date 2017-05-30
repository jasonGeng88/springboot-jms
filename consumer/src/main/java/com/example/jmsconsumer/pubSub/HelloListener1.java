package com.example.jmsconsumer.pubSub;

import com.example.jmsconsumer.Constant;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 发布/订阅模式: 消费者1
 * Created by jason-geng on 5/21/17.
 */
@Component
public class HelloListener1 {

    @JmsListener(destination = Constant.TOPIC_NAME, containerFactory = Constant.TOPIC_CONTAINER)
    public void receive(String msg){
        System.out.println("订阅者1 - " + msg);
    }
}
