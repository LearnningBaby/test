package com.admin.service.impl;

import com.admin.domain.Message;
import com.admin.mapper.MsgMapper;
import com.admin.service.MsgService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MsgServiceImpl extends ServiceImpl<MsgMapper, Message> implements MsgService {

    @Resource
    private MsgMapper msgMapper;

    @Override
    public List<Message> getMsg(Long adminNumb) {
        Long msgNumb = msgMapper.getMsgNumb(adminNumb);
        QueryWrapper<Message> wrapper = new QueryWrapper<>();
        wrapper.eq("rec_msg_numb",msgNumb);
        return msgMapper.selectList(wrapper);
    }

    @Override
    public void addMsg(Message message) {
        Long msgNumb = msgMapper.getMsgNumb(message.getSentMsgNumb());
        message.setSentMsgNumb(msgNumb);
        msgMapper.insert(message);
    }
}
