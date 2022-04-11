package com.teacher.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "cls_table",autoResultMap = true)
public class Class {
    private Long clsNumb;
    private Long insNumb;
    private String clsName;
    private Long clsQutity;
    private Long clsLevel;
}
