package com.teacher.domain;

import lombok.Data;

import java.util.List;

@Data
public class StuAnswer {

    private List<List<String>> answer;
    private Integer number;
}
