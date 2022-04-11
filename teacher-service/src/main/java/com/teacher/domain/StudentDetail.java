package com.teacher.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "stu_detial_tb",autoResultMap = true)
public class StudentDetail {
    private Long insNumb;
    private Long stuId;
    private String stuName;
    private Integer gender;
    private String perHead;
    private String clsName;
}
