package com.teacher.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.teacher.domain.Class;
import com.teacher.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TestMapper extends BaseMapper<Test> {

    @Select("select cls_qutity from cls_table where cls_numb=#{parseInt}")
    Integer getClsQutity(Long parseInt);

    @Select("select cls_numb from test_plan_tb where test_numb=#{testNumb}")
    Long getClsNumb(Long testNumb);

    @Select("select cls_name from cls_table where cls_numb=#{clsNumb}")
    String getClsName(Long clsNumb);

    @Select("select * from cls_table where cls_numb=#{parseLong}")
    Class getClsInfo(long parseLong);

    @Select("select * from stu_detial_tb where cls_name=#{clsName}")
    List<StudentDetail> getStu(String clsName);

    @Select("select stu_numb from stu_init_tb where ins_numb=#{ins_numb} and stu_id=#{stu_id}")
    Long getStuNumb(Long ins_numb, Long stu_id);

    @Select("select exam_status,stu_head,examRecord from stu_test_status where stu_numb=#{stuNumb}")
    TestStudent getStuStatus(Long stuNumb);

    @Select("select count(exam_status) from stu_test_status where cls_numb=#{clsNumb} and exam_status=#{status}")
    Long getTestCount(Long clsNumb,Integer status);

    @Update("update stu_test_status set exam_status=#{examStatus} where stu_numb=#{stuNumb}")
    void resetTest(Long stuNumb,Integer examStatus);

    @Select("select ins_numb from stu_detial_tb where stu_id=#{stuId} and stu_name=#{stuName}")
    Long getInsNumb(Long stuId, String stuName);

    @Select("select * from exam_tb where exam_numb=#{examNumb}")
    Exam getExam(Long examNumb);

    @Select("select exam_numb from test_plan_tb where test_numb=#{testNumb}")
    Long getExamNumb(Long testNumb);

    @Select("select stu_numb from stu_exam_tb where exam_numb=#{examNumb}")
    List<Long> getStuNumbList(Long examNumb);
}
