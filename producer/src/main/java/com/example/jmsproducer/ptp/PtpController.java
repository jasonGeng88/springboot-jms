package com.example.jmsproducer.ptp;

import com.example.jmsproducer.mapper.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 点对点模式 - 调用生产者
 * Created by jason-geng on 5/21/17.
 */
@RestController
@RequestMapping(value = "/ptp")
public class PtpController {

    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private PtpProducer ptpProducer;

    @RequestMapping(value = "/send")
    public Object send(){
        ptpProducer.send();
        return "success";
    }

    @RequestMapping(value = "/convertAndSend")
    public Object convertAndSend(){
        ptpProducer.convertAndSend();
        return "success";
    }

    @RequestMapping(value = "/multiDataSend")
    @Transactional
    public Object multiDataSend(){
        cityMapper.insert("1");
        ptpProducer.send();
//        cityMapper.findByState("CA");

        return cityMapper.count();
    }
}
