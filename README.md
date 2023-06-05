# CZY-Quartz-Trigger
Quartz任务调度器开发组件

## 实体类：

### QuartzTriggerInfoDTO 类属性如下：

```java
    @ApiModelProperty("服务名称")
    private String serverName;

    @ApiModelProperty("目标调用接口url")
    private String url;

    @ApiModelProperty("开始执行时间")
    private Long exeTime;

    @ApiModelProperty("任务名，要唯一")
    private String jobName;

    @ApiModelProperty("组名")
    private String groupName;

    @ApiModelProperty("循环执行的时间 时")
    private Integer hour;

    @ApiModelProperty("循环执行的时间 分")
    private Integer minute;

    @ApiModelProperty("循环执行的时间 秒")
    private Integer second;
```



### QuartzTriggerDeleteDTO  类属性如下：

```java
    @ApiModelProperty("任务名称")
    private String jobName;

    @ApiModelProperty("组名 or 服务名称")
    private String groupName;
```



## 接口

#### **exeQuartzTriggerTask(QuartzTriggerInfoDTO quartzTriggerInfoDTO)**

- 封装好上面**QuartzTriggerInfoDTO** 作为参数，调用QuartzTriggerClient 类中的**exeQuartzTriggerTask(QuartzTriggerInfoDTO quartzTriggerInfoDTO)**接口，便可根据exeTime在对应的时间点去调用url对应的方法、接口。
- 析：会根据exeTime这个长整数值解析出对应的成年月日时分秒来设置定时任务调度。


#### cancelQuartzTriggerTask(QuartzTriggerDeleteDTO quartzTriggerDeleteDTO)

- 封装好上面**QuartzTriggerDeleteDTO** 作为参数，调用QuartzTriggerClient 类中的**cancelQuartzTriggerTask(QuartzTriggerDeleteDTO quartzTriggerDeleteDTO)**接口，便可根据groupName和jobName对应的jobKey来取消删除定时任务。

  

#### exeQuartzTriggerTaskInTimeEveryDay( QuartzTriggerInfoDTO quartzTriggerInfoDTO ) 

- 封装好 上面的**QuartzTriggerInfoDTO** 作为参数，调用QuartzTriggerClient 类中的**exeQuartzTriggerTaskInTimeEveryDay**接口，便可根据hour、minute、second来在每一天中的 时:分:秒 执行url接口任务。

#### 
