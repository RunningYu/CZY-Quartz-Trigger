package com.quartztriggerutils;

import com.quartztriggerutils.job.QuartzJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2023/4/21
 */
public class quartzMain {
    public static void main(String[] args) {
        JobDetail jobDetail = JobBuilder.newJob(QuartzJob.class)
                // name: 任务名称（在调度器里不能重复，唯一的） group : 组
                .withIdentity("jop1", "group1")
                .usingJobData("job", "jobDetail1")
                .usingJobData("name", "jobDetail2")
                .usingJobData("count1", 0)
                .build();

        int count = 0;
		

        // 触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "trigger1")
                .usingJobData("trigger", "trigger")
                // 会覆盖上面JobDetail中的name的值
                .usingJobData("name", "trigger2")
                .usingJobData("count", count)
                .startNow()
                //                                                            时间间隔           永久重复执行
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())
                .build();

        // 调度器
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.scheduleJob(jobDetail, trigger);
            // 启动
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
