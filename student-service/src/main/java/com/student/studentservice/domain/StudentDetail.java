package com.student.studentservice.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "stu_detial_tb",autoResultMap = true)
public class StudentDetail {
    private Long ins_numb;
    private Long stu_id;
    private String stu_name;
    private Integer gender;
    private String per_head;
    private String cls_name;
}
