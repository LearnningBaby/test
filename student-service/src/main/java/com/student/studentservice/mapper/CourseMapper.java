package com.student.studentservice.mapper;

import com.student.studentservice.domain.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Mapper
public interface CourseMapper  {
    @Select("select crs_numb from stu_crs_tb where stu_numb=#{stu_numb}")
    List<Long> getCourseNumb(Long stu_numb);

    @Select("select * from crs_table where crs_numb=#{crs_numb}")
    Course getCourseInfo(Long crs_numb);
}
