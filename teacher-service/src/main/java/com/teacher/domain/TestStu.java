package com.teacher.domain;

import lombok.Data;

import java.util.List;

@Data
public class TestStu {

    private List<TestStudent> testStudent;
    private Long totalNumber;
    private Long testNumber;
    private Long submitNumber;
    private Long missNumber;
}
