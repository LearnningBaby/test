package com.student.studentservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.student.studentservice.domain.Course;
import com.student.studentservice.domain.Exam;
import com.student.studentservice.domain.Test;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TestMapper extends BaseMapper<Test> {

    @Select("select crs_numb from stu_crs_tb where stu_numb=#{stu_numb}")
    List<Long> getCrsNumb(Long stu_numb);

    @Select("select * from crs_table where crs_numb=#{crsNumb}")
    Course getCrsName(Long crsNumb);

    @Select("select * from exam_tb where exam_numb=#{examNumb}")
    Exam getExam(Long examNumb);

    @Select("select * from exam_tb where crs_numb=#{crsNumb}")
    Exam getExamName(Long crsNumb);

    @Select("select exam_numb from test_plan_tb where test_numb=#{testNumb}")
    Long getExamNumb(Long testNumb);

    @Select("select check_method from test_plan_tb where test_numb=#{testNumb}")
    Integer getCheckMethod(Integer testNumb);

    @Select("select per_head from stu_detial_tb where ins_numb=#{ins_numb} and stu_id=#{stu_id}")
    String getStuHead(Long ins_numb, Long stu_id);

    @Update("update stu_test_status set stu_head=#{image} where stu_numb=#{stuNumb}")
    void addHead(String image,Long stuNumb);
}
