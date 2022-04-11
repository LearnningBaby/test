package com.website.damian;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
public class TeacherAll {
    private Long teaNumb;
    private Long insNumb;
    private Long teaWno;
    private String password;
    private String nickPhoto;
    private Long teaPhone;
    private String email;
    private Integer onlineStatus;
    private String insName;
    private String checkpassword;
}
