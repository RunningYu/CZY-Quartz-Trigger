package com.quartztriggerutils.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2023/4/26
 */
@ApiModel("任务调度的信息体")
@Data
public class QuartzTriggerInfoDTO {

    @ApiModelProperty("服务名称")
    private String serverName;

    @ApiModelProperty("目标调用接口url")
    private String url;

    @ApiModelProperty("开始执行时间")
    private Long exeTime;

    @ApiModelProperty("任务名（唯一）")
    private String jobName;

    @ApiModelProperty("组名")
    private String groupName;

    @ApiModelProperty("循环执行的时间 时")
    private Integer hour;

    @ApiModelProperty("循环执行的时间 分")
    private Integer minute;

    @ApiModelProperty("循环执行的时间 秒")
    private Integer second;
}
