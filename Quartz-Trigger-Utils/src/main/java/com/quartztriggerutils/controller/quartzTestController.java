package com.quartztriggerutils.controller;

import com.quartztriggerutils.client.QOkHttpClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2023/4/22
 */
@Slf4j
@RestController
public class quartzTestController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/quartz")
    public String quart() {
        log.info("into---------->quartzController quartz");
        return "成功调用";
    }

    @GetMapping("/quartz1")
    public String quart1() {
        log.info("into---------->quartzController quartz1");

        String url = "http://localhost:8080/quartz";
        QOkHttpClient.getMapping(url);
        return "成功调用";
    }

}
