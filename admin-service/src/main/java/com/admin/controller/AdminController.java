package com.admin.controller;

import com.admin.domain.*;
import com.admin.filter.BaseResponse;
import com.admin.service.impl.AdminServiceImpl;
import com.admin.service.impl.MsgServiceImpl;
import com.admin.service.impl.TaskServiceImpl;
import com.admin.utils.emailUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@BaseResponse
@RestController
//@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private MsgServiceImpl msgService;

    @Autowired
    private TaskServiceImpl taskService;

    @PostMapping("/login")
    public R login(@RequestBody Admin admin){
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("admin_numb",admin.getAdminNumb())
                .eq("password",admin.getPassword());
        if (adminService.getOne(wrapper) == null){
            return new R(false,"用户名或密码错误!",null);
        }else {
            return new R(true,"登录成功!",null);
        }
    }

    @GetMapping("/userInfo/{adminNumb}")
    public Admin getAdminInfo(@PathVariable Long adminNumb){
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("admin_numb",adminNumb);
        Admin one = adminService.getOne(wrapper);
        one.setPassword(null);
        return one;
    }

    @PutMapping("/userInfo")
    public R updateAdminInfo(@RequestBody Admin admin){
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("admin_numb",admin.getAdminNumb());
        Admin admin1 = new Admin();
        admin1.setEmail(admin.getEmail());
        admin1.setNickHead(admin.getNickHead());
        adminService.update(admin1,wrapper);
        return new R(true,"修改成功!",null);
    }

    @PutMapping("/updatePassword")
    public R updatePassword(@RequestBody Password password){
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("admin_numb",password.getAdminNumb());
        wrapper.eq("password",password.getPassword());
        Admin one = adminService.getOne(wrapper);
        if (one==null){
            return new R(false,"原密码错误~",null);
        }else {
            Admin admin = new Admin();
            admin.setPassword(password.getNewPassword());
            adminService.update(admin,wrapper);
            return new R(true,"修改成功~",null);
        }
    }

    @PostMapping("/findPassword")
    public R findPassword(@RequestBody Password password){
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("admin_numb",password.getAdminNumb())
                .eq("email",password.getEmail());
        Admin one = adminService.getOne(wrapper);
        if (one!=null){
            emailUtils emailUtils = new emailUtils(password.getEmail());
            emailUtils.run();
            return new R(true,"请查看邮箱信息!",null);
        }else {
            return new R(false,"邮箱不正确!",null);
        }
    }

    @PostMapping("/findPassword2")
    public R findPd(@RequestBody Password password){
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("admin_numb",password.getAdminNumb());
        Admin admin = new Admin();
        admin.setPassword(password.getPassword());
        boolean one = adminService.update(admin,wrapper);
        if (!one){
            return new R(false,"密码错误!",null);
        }else {
            return new R(true,"找回成功!",null);
        }
    }

    @GetMapping("/message/{adminNumb}")
    public List<Message> getMsg(@PathVariable Long adminNumb){
        return msgService.getMsg(adminNumb);
    }

    @DeleteMapping("/message/{msgId}")
    public R delMsg(@PathVariable Long msgId){
        QueryWrapper<Message> wrapper = new QueryWrapper<>();
        wrapper.eq("msg_id",msgId);
        msgService.remove(wrapper);
        return new R(true,"删除成功!",null);
    }

    @DeleteMapping("/delAllMsg/{adminNumb}")
    public R delAllMsg(@PathVariable Long adminNumb){
        QueryWrapper<Message> wrapper = new QueryWrapper<>();
        wrapper.eq("rec_msg_numb",adminNumb);
        msgService.remove(wrapper);
        return new R(true,"删除成功!",null);
    }

    @PostMapping("/message")
    public R addMsg(@RequestBody Message message){
        msgService.addMsg(message);
        return new R(true,"发布成功!",null);
    }

    @GetMapping("/task/{adminNumb}")
    public List<Task> getTask(@PathVariable Long adminNumb){
        return taskService.getTask(adminNumb);
    }

    @PostMapping("/task")
    public R addTask(@RequestBody Task task){
        taskService.addTask(task);
        return new R(true,"添加成功!",null);
    }

    @DeleteMapping("/task/{taskNumb}")
    public R delTask(@PathVariable Long taskNumb){
        QueryWrapper<Task> wrapper = new QueryWrapper<>();
        wrapper.eq("task_numb",taskNumb);
        taskService.remove(wrapper);
        return new R(true,"删除成功!",null);
    }


}
