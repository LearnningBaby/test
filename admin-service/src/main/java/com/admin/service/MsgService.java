package com.admin.service;

import com.admin.domain.Message;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface MsgService extends IService<Message> {
    List<Message> getMsg(Long adminNumb);

    void addMsg(Message message);
}
