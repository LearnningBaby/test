package com.admin.mapper;

import com.admin.domain.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MsgMapper extends BaseMapper<Message> {

    @Select("select msg_numb from account_msg_map where admin_numb=#{adminNumb}")
    Long getMsgNumb(Long adminNumb);
}
