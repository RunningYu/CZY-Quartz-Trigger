package com.quartztriggerutils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;


@SpringBootTest
class QuartzTriggerUtilsApplicationTests {

    @Autowired
    private RestTemplate restTemplatel;



    @Test
    void contextLoads() {
//        String s = restTemplatel.getForObject("quartservice/quartz", String.class);

        String s = restTemplatel.getForObject("http://localhost:8081/quartz", String.class);

        System.out.println( s );
    }

}
