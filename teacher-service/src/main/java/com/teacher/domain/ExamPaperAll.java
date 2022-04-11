package com.teacher.domain;

import lombok.Data;

import java.util.List;

@Data
public class ExamPaperAll {

    private List<Object> paperList;
    private List<TitleInfo> infoList;
}
