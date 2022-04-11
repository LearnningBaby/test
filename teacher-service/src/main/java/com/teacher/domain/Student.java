package com.teacher.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "stu_init_tb",autoResultMap = true)
public class Student {
    private Long stuNumb;
    private Long insNumb;
    private Long stuId;
    private Long clsNumb;
    private String password;
    private String nickName;
    private String email;
    private Long phone;
    private String nickPhoto;
    private Integer onlineStatus;
    private Integer number;
}
