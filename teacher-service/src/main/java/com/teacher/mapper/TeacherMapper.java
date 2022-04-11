package com.teacher.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.teacher.domain.Teacher;
import com.teacher.domain.TeacherDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {

    @Select("select * from tea_detial_tb where ins_numb=#{insNumb}")
    TeacherDetail getTeadetail(Long insNumb);
}
