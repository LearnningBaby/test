package com.student.studentservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.student.studentservice.domain.StuRes;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ResMapper extends BaseMapper<StuRes> {
}
