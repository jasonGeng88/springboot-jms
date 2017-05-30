package com.example.jmsconsumer.ptp;

import com.example.jmsconsumer.Constant;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 点对点模式: 消费者1
 * Created by jason-geng on 5/21/17.
 */
@Component
public class PtpListener1 {

    /**
     * 消息队列监听器
     * destination 队列地址
     * containerFactory 监听器容器工厂, 若存在2个以上的监听容器工厂,需进行指定
     */
    @JmsListener(destination = Constant.QUEUE_NAME, containerFactory = Constant.QUEUE_CONTAINER)
    public void receive(String msg){
        System.out.println("点对点模式1: " + msg);
        //抛出异常后,消息不会被消费成功,将会进行重试,达到次数后,进入死信队列
//        throw new RuntimeException();
    }
}
