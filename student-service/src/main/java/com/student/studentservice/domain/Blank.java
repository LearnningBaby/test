package com.student.studentservice.domain;

import lombok.Data;

import java.util.List;

@Data
public class Blank extends Qu{
    private Long id;
    private String desc;
    private List<String> ans;
    private String explain;
}
