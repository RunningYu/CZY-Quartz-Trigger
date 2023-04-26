package com.quartztriggerutils.runner;

import com.quartztriggerutils.bean.QuartzTriggerDeleteDTO;
import com.quartztriggerutils.bean.QuartzTriggerInfoDTO;
import com.quartztriggerutils.events.QuartzTriggerClient;
import io.swagger.annotations.ApiModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2023/4/21
 */
@ApiModel("测试quartz，启动项目时会随着一起启动该类")
@Slf4j
@Component
@Order(1)
public class quartzTaskApplicationRunner implements ApplicationRunner {

    @Resource
    private QuartzTriggerClient quartzTriggerClient;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("------------>quartzTaskApplicationRunner run ");
        QuartzTriggerInfoDTO quartzTriggerInfoDTO = new QuartzTriggerInfoDTO();
        quartzTriggerInfoDTO.setGroupName("group1");
        quartzTriggerInfoDTO.setJobName("job1");
        quartzTriggerInfoDTO.setUrl("http://localhost:8080/quartz");
        quartzTriggerInfoDTO.setExeTime(System.currentTimeMillis() + 1000 * 10);
        quartzTriggerClient.exeQuartzTriggerTask(quartzTriggerInfoDTO);
        QuartzTriggerDeleteDTO quartzTriggerDeleteDTO = new QuartzTriggerDeleteDTO();
        quartzTriggerDeleteDTO.setGroupName("group1");
        quartzTriggerDeleteDTO.setJobName("job1");
        quartzTriggerClient.cancelQuartzTriggerTask(quartzTriggerDeleteDTO);
    }
}
