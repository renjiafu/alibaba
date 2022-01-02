package com.rjf.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class TestController {

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CallableProvider callableProvider;

    @GetMapping("/")
    public String index(){
        return "Consumer !";
    }

    @GetMapping("/remote01/{appName}")
    public String remote01(@PathVariable("appName") String appName){

        log.info("Consumer call {}-->",appName);

        //使用 LoadBalanceClient 和 RestTemolate 结合的方式来访问
        ServiceInstance serviceInstance = loadBalancerClient.choose(appName);
        String url = String.format("http://%s:%s",serviceInstance.getHost(),serviceInstance.getPort());
        log.info("request url:{}",url);

        String response = restTemplate.getForObject(url, String.class);
        return  "Consumer call get => "+response;
    }

    @GetMapping("/remote02/provider")
    public String remote02(){

        String response = callableProvider.index();
        log.info(response);

        return  "Consumer call get => "+response;
    }

}
