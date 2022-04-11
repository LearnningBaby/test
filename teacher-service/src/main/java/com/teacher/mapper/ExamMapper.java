package com.teacher.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.teacher.domain.Exam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ExamMapper extends BaseMapper<Exam> {

    @Select("select bank_numb from bank_table where crs_numb=#{crsNumb}")
    Long getBankNumb(Long crsNumb);

    @Select("select exam_numb from exam_tb where exam_name=#{examName}")
    Long getExamNumb(String examName);
}
