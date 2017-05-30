package com.example.jmsconsumer.ptp;

import com.example.jmsconsumer.Constant;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 点对点模式: 消费者2
 * Created by jason-geng on 5/21/17.
 */
@Component
public class PtpListener2 {

    @JmsListener(destination = Constant.QUEUE_NAME, containerFactory = Constant.QUEUE_CONTAINER)
    public void receive(String msg){
        System.out.println("点对点模式2: " + msg);
    }
}
