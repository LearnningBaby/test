package com.website.controller;

import com.website.damian.R;
import com.website.damian.StudentAll;
import com.website.damian.TeacherAll;
import com.website.service.impl.StuServiceImpl;
import com.website.service.impl.TeaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/aaa")
public class controller {
    @Autowired
    private StuServiceImpl stuService;

    @Autowired
    private TeaServiceImpl teaService;

    @PostMapping("/stuRegister")
    public R stuRegister(StudentAll student){
        boolean b = stuService.stuRegister(student);
        if (b){
            return new R(true,"注册成功!",null);
        }else {
            return new R(false,"注册失败!",null);
        }

    }

    @PostMapping("/stuLogin")
    public R stuLogin(StudentAll student){
        boolean b = stuService.stuLogin(student);
        if (b){
            return new R(true,"登录成功!",null);
        }else {
            return new R(false,"账号或密码错误!",null);
        }
    }

    @PostMapping("/teaRegister")
    public R teaRegister(TeacherAll teacher){
        boolean b = teaService.teaRegister(teacher);
        if (b){
            return new R(true,"注册成功!",null);
        }else {
            return new R(false,"注册失败!",null);
        }

    }

    @PostMapping("/teaLogin")
    public R teaLogin(TeacherAll teacher){
        boolean b = teaService.teaLogin(teacher);
        if (b){
            return new R(true,"登录成功!",null);
        }else {
            return new R(false,"账号或密码错误!",null);
        }
    }
}
