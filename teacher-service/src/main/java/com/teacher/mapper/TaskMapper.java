package com.teacher.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.teacher.domain.Task;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TaskMapper extends BaseMapper<Task> {
}
