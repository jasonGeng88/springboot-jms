package com.example.jmsconsumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by jason-geng on 5/14/17.
 */
@Component
public class HelloListener {

    @JmsListener(destination = "hello")
    public void process(String msg){
        System.out.println(msg);
    }
}
