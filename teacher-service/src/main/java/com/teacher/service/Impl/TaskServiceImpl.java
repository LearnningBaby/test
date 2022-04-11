package com.teacher.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teacher.domain.Task;
import com.teacher.mapper.MsgMapper;
import com.teacher.mapper.TaskMapper;
import com.teacher.service.TaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {

    @Resource
    private TaskMapper taskMapper;

    @Resource
    private MsgMapper msgMapper;

    @Override
    public List<Task> getTask(Long teaNumb) {
        Long msgNumb = msgMapper.getMsgNumb(teaNumb);
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
