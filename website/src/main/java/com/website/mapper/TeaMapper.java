package com.website.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.website.damian.Teacher;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeaMapper extends BaseMapper<Teacher> {

    @Insert("insert into tea_detial_tb (ins_numb,tea_wno) values(#{insNumb},#{teaWno})")
    void addTea(Teacher teacher1);
}
