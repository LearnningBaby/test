package com.student.studentservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.student.studentservice.domain.Student;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StuMapper extends BaseMapper<Student> {
}
