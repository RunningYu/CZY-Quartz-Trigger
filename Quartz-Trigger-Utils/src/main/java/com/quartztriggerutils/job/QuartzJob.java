package com.quartztriggerutils.job;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2023/4/21
 */

import com.quartztriggerutils.client.QOkHttpClient;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.scheduling.quartz.QuartzJobBean;


/**
 * @DisallowConcurrentExecution :
 *      禁止并发地执行通过一个 job 定义（JobDetail定义的）的多个实例
 * @PersistJobDataAfterExecution :
 *      持久化 JobDetail 中的 JobDataMap (对 trigger 中的 datamap 无效)
 *      如果一个任务不是持久化的，则当没有触发器关联它的时候，Quartz会从scheduler中删除它
 */
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class QuartzJob extends QuartzJobBean {

    /**
     * 如果在添加 .usingJobData("url", "xxxx") 的时候，
     * key 和 这里定义的属性名一样的话，就会给这里的属性赋值，下面就可以直接用了
     */
    private String url;

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) {
        QOkHttpClient.getMapping(url);
    }
}