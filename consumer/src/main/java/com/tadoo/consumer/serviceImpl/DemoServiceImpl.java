package com.tadoo.consumer.serviceImpl;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tadoo.consumer.hystrix.HelloServiceCommand;
import com.tadoo.consumer.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DemoServiceImpl implements DemoService {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    //请求熔断注解，当服务出现问题时候会执行fallbackMetho属性的名为helloFallBack的方法
    @HystrixCommand(fallbackMethod = "processHystrix_Get")
    public String consumerDemo(){
//        HelloServiceCommand command = new HelloServiceCommand(restTemplate);
        String //result = command.execute();
        result = this.restTemplate.getForObject("http://spring-cloud-provider/provider/demo", String.class);
        return result;
    }

    public String processHystrix_Get() {
        return "ERROR";
    }
}
