package com.teacher.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.teacher.domain.Task;

import java.util.List;

public interface TaskService extends IService<Task> {
    List<Task> getTask(Long teaNumb);

    void addTask(Task task);
}
