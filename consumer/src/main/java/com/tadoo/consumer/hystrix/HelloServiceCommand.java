package com.tadoo.consumer.hystrix;

import com.netflix.hystrix.*;
import org.springframework.web.client.RestTemplate;

public class HelloServiceCommand extends HystrixCommand<String> {

    private RestTemplate restTemplate;
    public HelloServiceCommand(RestTemplate restTemplate) {
        super(Setter
                // 分组名称用于统计
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("userGroup"))
                // 用于监控、熔断、度量发布、缓存的Key值
                .andCommandKey(HystrixCommandKey.Factory.asKey("userCommandKey"))
                // 线程池命名，默认是HystrixCommandGroupKey的名称。
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("userThreadPool"))
                // command 熔断相关参数配置
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        // 隔离方式：线程池和信号量。默认使用线程池
                        // .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                        // 超时时间500毫秒
                        // .withExecutionTimeoutInMilliseconds(500)
                        // 信号量隔离的模式下，最大的请求数。和线程池大小的意义一样
                        // .withExecutionIsolationSemaphoreMaxConcurrentRequests(2)
                        // 熔断时间（熔断开启后，各5秒后进入半开启状态，试探是否恢复正常）
                        .withCircuitBreakerSleepWindowInMilliseconds(10000)
                )
                // 设置线程池参数
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        // 线程池大小
                        .withCoreSize(1)));
                this.restTemplate = restTemplate;
    }
    //服务调用
    @Override
    protected String run() throws Exception {
        return this.restTemplate.getForObject("http://spring-cloud-provider/provider/demo", String.class);
    }
    //服务降级时所调用的Fallback()
    @Override
    protected String getFallback() {
        return "fallback";
    }
}
