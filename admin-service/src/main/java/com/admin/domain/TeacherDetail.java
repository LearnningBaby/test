package com.admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "tea_detial_tb",autoResultMap = true)
public class TeacherDetail {
    private Long insNumb;
    private Long teaWno;
    private String teaDepart;
    private String teaName;
    private String teaDegree;
    private String teaTitle;
    private String perPhoto;

}
