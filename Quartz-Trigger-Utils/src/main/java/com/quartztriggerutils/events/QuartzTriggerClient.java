package com.quartztriggerutils.events;

import com.mchange.v2.lang.StringUtils;
import com.quartztriggerutils.bean.QuartzTriggerDeleteDTO;
import com.quartztriggerutils.bean.QuartzTriggerInfoDTO;
import com.quartztriggerutils.job.QuartzJob;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2022/12/17
 */
@ApiModel(description = "任务，事件")
@Configuration
@Slf4j
public class QuartzTriggerClient {

    /**
     * 注入调度器
     */
    @Autowired
    private Scheduler scheduler;

    /**
     * 定时执行任务
     */
    public void exeQuartzTriggerTask(QuartzTriggerInfoDTO quartzTriggerInfoDTO) {

        String[] dates = getDates(quartzTriggerInfoDTO.getExeTime());
        TriggerKey triggerKey = TriggerKey.triggerKey(quartzTriggerInfoDTO.getJobName(), quartzTriggerInfoDTO.getGroupName());
        String trrigerTime = dates[5] + " " + dates[4] + " " + dates[3] + " " + dates[2] + " " + dates[1] + " ? " + dates[0];

        log.info("QuartzEvents quartTriggerInTime triggerTime:{}", dates[0] + "-" + dates[1] + "-" + dates[2] + "- " + dates[3] + ":" + dates[4] + ":" + dates[5]);

        try {
            /*
             在调度器里根据triggerKey来取 Trigger触发器, 当调度器其中没有对应的触发器时，就创建一个触发器
             这样写好处是可以确保 这个触发器在调度器中是唯一的（而且因为同一个策略中的触发器只需要一个实例就可以了）
             */
            Trigger trigger = scheduler.getTrigger(triggerKey);
            if (trigger == null) {
                trigger = TriggerBuilder.newTrigger()
                        .withIdentity(triggerKey)
                        .usingJobData("url", quartzTriggerInfoDTO.getUrl())
                        .withSchedule(CronScheduleBuilder.cronSchedule(trrigerTime))
                        .startNow()
                        .build();
                //                                      封装job
                JobDetail jobDetail = JobBuilder.newJob(QuartzJob.class)
                        .withIdentity(quartzTriggerInfoDTO.getJobName(), quartzTriggerInfoDTO.getGroupName())
                        .build();
                scheduler.scheduleJob(jobDetail, trigger);
                scheduler.start();
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 取消定时任务
     */
    public void cancelQuartzTriggerTask(QuartzTriggerDeleteDTO quartzTriggerDeleteDTO) {
        // todo 优化 -> 先判断是否存在该定时任务

        JobKey jobKey = new JobKey(quartzTriggerDeleteDTO.getJobName(), quartzTriggerDeleteDTO.getGroupName());
        log.info("QuartzTriggerClient cancelQuartzTriggerTask -> cancel this task of jobKey:{}", jobKey);
        try {
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            log.error("QuartzTriggerClient cancelQuartzTriggerTask no this jobKey:{}", jobKey);
        }

    }



    /**
     * 毫秒值 --> 格式化时间 --> 数组
     * @return 数组
     */
    private String[] getDates(Long exeTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy MM dd HH mm ss");
        String timeStr = simpleDateFormat.format(exeTime);
        if (StringUtils.nonEmptyString(timeStr)) {
            return timeStr.split(" ");
        }
        return null;
    }

    @ApiOperation("更新同步")
    public void exeQuartzTriggerTaskInTimeEveryDay(QuartzTriggerInfoDTO quartzTriggerInfoDTO) {
        TriggerKey triggerKey = TriggerKey.triggerKey(quartzTriggerInfoDTO.getJobName(), quartzTriggerInfoDTO.getGroupName());
        String triggerTime = quartzTriggerInfoDTO.getSecond() + " " + quartzTriggerInfoDTO.getMinute() + " " + quartzTriggerInfoDTO.getHour() + " * * ? *";
        try {
            Trigger trigger = scheduler.getTrigger(triggerKey);
            if (trigger == null) {
                trigger = TriggerBuilder.newTrigger()
                        .withIdentity(triggerKey)
                        .withSchedule(CronScheduleBuilder.cronSchedule(triggerTime))
                        .startNow()
                        .build();
                JobDetail jobDetail = JobBuilder.newJob(QuartzJob.class)
                        .withIdentity(quartzTriggerInfoDTO.getJobName(), quartzTriggerInfoDTO.getGroupName())
                        .build();
                scheduler.scheduleJob(jobDetail, trigger);
                scheduler.start();
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
