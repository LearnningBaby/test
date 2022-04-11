package com.student.studentservice.domain;

import lombok.Data;
import java.util.List;
@Data
public class ExamPaperAll {

    private List<Object> paperList;
    private List<TitleInfo> infoList;
}
