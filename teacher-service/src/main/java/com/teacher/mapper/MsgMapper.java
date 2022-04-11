package com.teacher.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.teacher.domain.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MsgMapper extends BaseMapper<Message> {

    @Select("select msg_numb from account_msg_map where tea_numb=#{teaNumb}")
    Long getMsgNumb(Long teaNumb);
}
