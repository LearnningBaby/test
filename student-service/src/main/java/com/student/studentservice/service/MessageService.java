package com.student.studentservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.student.studentservice.domain.Message;

import java.util.List;

public interface MessageService extends IService<Message> {
    List<Message> getMessage(Integer stu_numb);

    boolean delById(Integer msgId);

    boolean delAll(Integer stu_numb);
}
