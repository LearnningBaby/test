package com.teacher.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("stu_exam_res")
public class StuRes {

    private Long resNumb;
    private Long stuNumb;
    private Long examNumb;
    private Integer selectRes;
    private Integer spaceRes;
    private Integer shortRes;
    private Integer grade;
}
