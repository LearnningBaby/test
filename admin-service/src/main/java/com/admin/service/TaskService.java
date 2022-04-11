package com.admin.service;

import com.admin.domain.Task;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface TaskService extends IService<Task> {
    List<Task> getTask(Long adminNumb);

    void addTask(Task task);
}
