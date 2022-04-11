package com.student.studentservice.domain;

import lombok.Data;

import java.util.List;

@Data
public class ExamPaper {
    private List<Select> selectList;
    private List<Blank> blankList;
    private List<ShortAnswer> answerList;
}
