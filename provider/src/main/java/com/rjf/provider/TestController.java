package com.rjf.provider;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/index")
    @SentinelResource("/index")
    public String index(){
        return "Provider !";
    }
}
