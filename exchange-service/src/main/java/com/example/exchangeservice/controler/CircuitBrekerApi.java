package com.example.exchangeservice.controler;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBrekerApi {
    Logger logger = LoggerFactory.getLogger(CircuitBrekerApi.class);
    @GetMapping("/sample-api")
   // @Retry(name = "sample-api",fallbackMethod = "hardCodedResponse")
    @CircuitBreaker(name = "sample-api",fallbackMethod = "hardCodedResponse")
    public String sample(){
        logger.info("request Received");
        ResponseEntity<String> stringResponseEntity = new RestTemplate().getForEntity("hhhh",null,String.class);
        return stringResponseEntity.getBody();
    }

    String hardCodedResponse(Exception e){
        return "fallback-response";
    }
}
