package com.quartztriggerutils.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2023/4/26
 */
@ApiModel("删除任务调度的信息体")
@Data
public class QuartzTriggerDeleteDTO {

    @ApiModelProperty("任务名称")
    private String jobName;

    @ApiModelProperty("组名 or 服务名称")
    private String groupName;
}
