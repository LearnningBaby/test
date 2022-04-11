package com.student.studentservice.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("exam_tb")
public class Exam {
    private Long examNumb;
    private Long teaNUmb;
    private Long crsNumb;
    private String examName;
    private String body;
    private Integer sumScore;
    private Integer examLevel;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date createTime;

}
