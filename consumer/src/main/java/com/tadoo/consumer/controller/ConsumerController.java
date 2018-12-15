package com.tadoo.consumer.controller;

import com.tadoo.consumer.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Autowired
    private DemoService service;

    @RequestMapping("consumer/demo")
    public String ConsumerDemo(){
        return service.consumerDemo();
    }

}
