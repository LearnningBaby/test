package com.teacher.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ExamAll {
    private Long crsNumb;
    private String examName;
    private Integer examLevel;
    private Integer questSelectSingle;
    private Integer questSelect;
    private Integer questBlanks;
    private Integer questAnswer;
    private Integer questTufa;
    private Integer mode;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date createTime;
    private Long teaNumb;
}
