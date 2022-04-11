package com.admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName(value = "test_plan_tb",autoResultMap = true)
public class Test {
    private Long testNumb;
    private Long teaNumb;
    private Long crsNumb;
    private Long examNumb;
    private Long clsNumb;
    private String testName;
    private Integer testStatus;
    private Integer checkMethod;
    private Integer sumPeople;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date startTime;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date endTime;
    private Integer needTime;
}
