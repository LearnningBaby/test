package com.student.studentservice.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.student.studentservice.domain.Message;
import com.student.studentservice.mapper.MessageMapper;
import com.student.studentservice.service.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper,Message> implements MessageService {

    @Resource
    private MessageMapper messageMapper;

    /**
     * 查询消息
     * @param stu_numb
     * @return
     */
    @Override
    public List<Message> getMessage(Integer stu_numb) {
        Long mesId = messageMapper.getMesId(stu_numb);
        QueryWrapper<Message> wrapper = new QueryWrapper<>();
        wrapper.eq("rec_msg_numb",mesId);
        return messageMapper.selectList(wrapper);
    }

    @Override
    public boolean delById(Integer msgId) {
        QueryWrapper<Message> wrapper = new QueryWrapper<>();
        wrapper.eq("msg_id",msgId);
        return messageMapper.delete(wrapper)>0;
    }

    @Override
    public boolean delAll(Integer stu_numb) {
        QueryWrapper<Message> wrapper = new QueryWrapper<>();
        wrapper.eq("rec_msg_numb",stu_numb);
        return messageMapper.delete(wrapper)>0;
    }
}
