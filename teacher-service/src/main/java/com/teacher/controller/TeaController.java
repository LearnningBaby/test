package com.teacher.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.teacher.domain.*;
import com.teacher.service.Impl.CourseServiceImpl;
import com.teacher.service.Impl.TeacherServiceImpl;
import com.teacher.utils.emailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeaController {

    @Autowired
    private TeacherServiceImpl teacherService;

    @Autowired
    private CourseServiceImpl courseService;

    @PostMapping("/login")
    public R login(@RequestBody Teacher teacher){
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        wrapper.eq("tea_numb",teacher.getTeaNumb())
                .eq("password",teacher.getPassword());
        Teacher one = teacherService.getOne(wrapper);
        if (one==null){
            return new R(false,"用户名或密码错误!",null);
        }else {
            return new R(true,"登录成功!",null);
        }
    }


    @GetMapping("/userinfo/{teaNumb}")
    public R getUserInfo(@PathVariable Long teaNumb){
        Tea one = teacherService.getUserInfo(teaNumb);
        return new R(true,"查询成功!",one);
    }

    @PutMapping("/userinfo")
    public R getUserInfo(@RequestBody Teacher teacher){
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        wrapper.eq("tea_numb",teacher.getTeaNumb());
        boolean one = teacherService.update(teacher, wrapper);
        if (one){
            return new R(true,"修改成功!",null);
        }else {
            return new R(false,"修改失败!",null);
        }
    }

    @PutMapping("/updatePd")
    public R updatePd(@RequestBody Password password){
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        wrapper.eq("tea_numb",password.getTeaNumb())
               .eq("password",password.getPassword());
        Teacher one1 = teacherService.getOne(wrapper);
        if (one1!=null){
            Teacher teacher = new Teacher();
            teacher.setPassword(password.getNewPassword());
            QueryWrapper<Teacher> wrapper2 = new QueryWrapper<>();
            wrapper2.eq("tea_numb",password.getTeaNumb());
            boolean one = teacherService.update(teacher, wrapper2);
            return new R(true,"修改成功!",null);
        }else {
            return new R(false,"原密码错误!",null);
        }
    }

    @PutMapping("/findPd")
    public R findPd(@RequestBody Password password){
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        wrapper.eq("tea_numb",password.getTeaNumb())
                .eq("email",password.getEmail());
        Teacher one = teacherService.getOne(wrapper);
        if (one==null){
            return new R(false,"邮箱不正确!",null);
        }else {
            emailUtils emailUtils = new emailUtils(password.getEmail());
            emailUtils.run();
            return new R(true,"请查看邮箱信息!",null);
        }
    }

    @PostMapping("/findPassword")
    public R findPd2(@RequestBody Password password){
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        wrapper.eq("tea_numb",password.getTeaNumb());
        Teacher teacher = new Teacher();
        teacher.setPassword(password.getPassword());
        boolean one = teacherService.update(teacher,wrapper);
        if (!one){
            return new R(false,"密码错误!",null);
        }else {
            return new R(true,"找回成功!",null);
        }
    }

    @GetMapping("/course/{teaNumb}")
    public R getCourse(@PathVariable Long teaNumb){
        List<CourseAll> course = courseService.getCourse(teaNumb);
        return new R(true,"查询成功!",course);
    }
}
