package com.teacher.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.teacher.domain.Message;

import java.util.List;

public interface MsgService extends IService<Message> {

    List<Message> getMsg(Long teaNumb);

    void addMsg(Message message);
}
