package com.teacher.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teacher.domain.Message;
import com.teacher.mapper.MsgMapper;
import com.teacher.service.MsgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MsgServiceImpl extends ServiceImpl<MsgMapper, Message> implements MsgService {

    @Resource
    private MsgMapper msgMapper;

    @Override
    public List<Message> getMsg(Long teaNumb) {
        Long msgNumb = msgMapper.getMsgNumb(teaNumb);
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
