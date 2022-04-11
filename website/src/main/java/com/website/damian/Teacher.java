package com.website.damian;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "tea_init_tb",autoResultMap = true)
public class Teacher {
    private Long teaNumb;
    private Long insNumb;
    private Long teaWno;
    private String password;
    private String nickPhoto;
    private Long teaPhone;
    private String email;
    private Integer onlineStatus;
}
