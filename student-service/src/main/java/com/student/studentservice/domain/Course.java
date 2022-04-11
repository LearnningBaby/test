package com.student.studentservice.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
@TableName(value = "crs_table",autoResultMap = true)
public class Course {
    private Long crsNumb;
    private Long insNumb;
    private String crsName;
    private String bookImg;
    private String crsDesc;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date createTime;
}
