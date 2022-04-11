package com.student.studentservice.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "stu_init_tb",autoResultMap = true)
public class Student {
    private Long stu_numb;
    private Long ins_numb;
    private Long stu_id;
    private Long cls_numb;
    private String password;
    private String nick_name;
    private String email;
    private Long phone;
    private String nick_photo;
    private Integer online_status;
}
