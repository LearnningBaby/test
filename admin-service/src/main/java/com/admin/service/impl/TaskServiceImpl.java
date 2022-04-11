package com.admin.service.impl;

import com.admin.domain.Task;
import com.admin.mapper.MsgMapper;
import com.admin.mapper.TaskMapper;
import com.admin.service.TaskService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper , Task> implements TaskService {

    @Resource
    private TaskMapper taskMapper;

    @Resource
    private MsgMapper msgMapper;

    @Override
    public List<Task> getTask(Long adminNumb) {
        Long msgNumb = msgMapper.getMsgNumb(adminNumb);
        QueryWrapper<Task> wrapper = new QueryWrapper<>();
        wrapper.eq("task_hand_numb",msgNumb);
        return taskMapper.selectList(wrapper);
    }

    @Override
    public void addTask(Task task) {
        Long msgNumb = msgMapper.getMsgNumb(task.getTaskHandNumb());
        task.setTaskHandNumb(msgNumb);
        taskMapper.insert(task);
    }
}
