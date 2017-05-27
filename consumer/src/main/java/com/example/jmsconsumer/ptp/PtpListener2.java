package com.example.jmsconsumer.ptp;

import org.springframework.jms.annotation.JmsListener;

/**
 * 点对点模式: 消费者2
 * Created by jason-geng on 5/21/17.
 */
//@Component
public class PtpListener2 {

    @JmsListener(destination = "ptp", containerFactory = "jmsQueueListenerContainerFactory")
    public void receive(String msg){
        System.out.println("点对点模式2: " + msg);
    }
}
