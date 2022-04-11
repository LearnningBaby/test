package com.admin.mapper;

import com.admin.domain.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StuMapper extends BaseMapper<Student> {
}
