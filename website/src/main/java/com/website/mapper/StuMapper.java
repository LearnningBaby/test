package com.website.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.website.damian.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StuMapper extends BaseMapper<Student> {

    @Select("select ins_numb from ins_table where ins_name=#{insName}")
    Long getInsNumb(String insName);

    @Insert("insert into stu_detial_tb (ins_numb,stu_id) values(#{insNumb},#{stuId})")
    void addStuDe(Student student1);
}
