package com.teacher.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.teacher.domain.Message;
import com.teacher.domain.R;
import com.teacher.domain.Task;
import com.teacher.service.Impl.MsgServiceImpl;
import com.teacher.service.Impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class MsgController {

    @Autowired
    private MsgServiceImpl msgService;

    @Autowired
    private TaskServiceImpl taskService;

    @GetMapping("/message/{teaNumb}")
    public R getMsg(@PathVariable Long teaNumb){
        List<Message> msg = msgService.getMsg(teaNumb);
        return new R(true,"查询成功!",msg);
    }

    @DeleteMapping("/message/{msgId}")
    public R delMsg(@PathVariable Long msgId){
        QueryWrapper<Message> wrapper = new QueryWrapper<>();
        wrapper.eq("msg_id",msgId);
        msgService.remove(wrapper);
        return new R(true,"删除成功!",null);
    }

    @DeleteMapping("/delAllMessage/{teaNumb}")
    public R delAllMsg(@PathVariable Long teaNumb){
        QueryWrapper<Message> wrapper = new QueryWrapper<>();
        wrapper.eq("rec_msg_numb",teaNumb);
        msgService.remove(wrapper);
        return new R(true,"删除成功!",null);
    }

    @PostMapping("/message")
    public R addMsg(@RequestBody Message message){
        msgService.addMsg(message);
        return new R(true,"发布成功!",null);
    }

    @GetMapping("/task/{teaNumb}")
    public R getTask(@PathVariable Long teaNumb){
        List<Task> task = taskService.getTask(teaNumb);
        return new R(true,"查询成功!",task);
    }

    @DeleteMapping("/task/{taskNumb}")
    public R delTask(@PathVariable Long taskNumb){
        QueryWrapper<Task> wrapper = new QueryWrapper<>();
        wrapper.eq("task_numb",taskNumb);
        taskService.remove(wrapper);
        return new R(true,"删除成功!",null);
    }

    @PostMapping("/task")
    public R addTask(@RequestBody Task task){
        taskService.addTask(task);
        return new R(true,"",null);
    }
}
