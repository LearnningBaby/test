package com.teacher.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class TestAll {
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
    private String crsName;
    private Integer sumScore;

}
