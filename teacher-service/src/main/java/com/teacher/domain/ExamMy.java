package com.teacher.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ExamMy {
    private Integer[] singleSelect;
    private Integer[] blanks;
    private Integer[] answer;
    private String examName;
    private Long crsNumb;
    private Long teaNumb;
    private Long examNumb;
    private String body;
    private String crsName;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date createTime;
    private Integer examLevel;
    private Integer sumScore;
    private Long bankNumb;
}
