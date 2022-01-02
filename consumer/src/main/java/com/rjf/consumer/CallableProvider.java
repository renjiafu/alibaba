package com.rjf.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="providerapp")
public interface CallableProvider {

    @GetMapping("/")
    String index();
}
