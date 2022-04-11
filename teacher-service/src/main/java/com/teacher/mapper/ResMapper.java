package com.teacher.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.teacher.domain.StuRes;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ResMapper extends BaseMapper<StuRes> {
}
