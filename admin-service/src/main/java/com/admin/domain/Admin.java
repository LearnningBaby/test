package com.admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("admin_tb")
public class Admin {
    private Long adminNumb;
    private String password;
    private String permLevel;
    private String email;
    private String nickHead;
    private Long simStuId;
    private Long simTeaWno;
    private Long simInsNumb;
    private Integer onlineStatus;
}
