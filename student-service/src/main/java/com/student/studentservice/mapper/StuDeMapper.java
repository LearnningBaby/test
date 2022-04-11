package com.student.studentservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.student.studentservice.domain.StudentDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StuDeMapper extends BaseMapper<StudentDetail> {
}
