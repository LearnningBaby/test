package com.student.studentservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.student.studentservice.domain.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {
    @Select("select msg_numb from account_msg_map where stu_numb=#{stu_numb}")
    Long getMesId(Integer stu_numb);
}
