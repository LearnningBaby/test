package com.teacher.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.teacher.domain.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseMapper extends BaseMapper<Course> {

    @Select("select crs_numb from tea_crs_tb where tea_numb=#{teaNumb}")
    List<Long> getCrsNumb(Long teaNumb);

    @Select("select cls_numb from cls_crs_tb where crs_numb=#{aLong}")
    List<Long> getClsNumb(Long aLong);

    @Select("select cls_name from cls_table where cls_numb=#{aLong1}")
    String getClsName(Long aLong1);

    @Select("select count(stu_numb) from stu_crs_tb where crs_numb=#{aLong}")
    Integer getStuNumber(Long aLong);
}
