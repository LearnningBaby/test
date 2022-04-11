package com.admin.domain;

import lombok.Data;

@Data
public class Qu {//题目父类
    protected Integer score;
    protected Long freLev;
    protected Integer dffLev;
    protected Integer QuType;
    protected boolean isShow;
}
