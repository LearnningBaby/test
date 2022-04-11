package com.teacher.domain;

import lombok.Data;

import java.util.List;

@Data
public class Select extends Qu{//选择题
    private Long id;
    private String desc;
    private List<String> choice;
    private String ans;
    private String explain;
}
