package com.admin.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class CourseAll {
    private Long crsNumb;
    private Long insNumb;
    private String crsName;
    private String bookImg;
    private String crsDesc;
    private String[] crsClass;
    private Integer crsNumber;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date createTime;
}
