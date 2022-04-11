package com.website.damian;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("ins_table")
public class Ins {
    private Long insNumb;
    private String insName;
    private String insAddress;
    private String insHead;
    private String contact;
    private String insLogo;
    private Integer insStatus;
}
