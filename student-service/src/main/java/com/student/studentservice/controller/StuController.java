package com.student.studentservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.student.studentservice.domain.*;
import com.student.studentservice.service.Impl.CourseServiceImpl;
import com.student.studentservice.service.Impl.MessageServiceImpl;
import com.student.studentservice.service.Impl.StuDeServiceImpl;
import com.student.studentservice.service.Impl.StuServiceImpl;
import com.student.studentservice.utils.emailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
//@RequestMapping("/student")
public class StuController {
    @Autowired
    private StuServiceImpl stuService;

    @Autowired
    private StuDeServiceImpl stuDeService;

    @Autowired
    private CourseServiceImpl courseService;

    @Autowired
    private MessageServiceImpl messageService;

    @PostMapping("/login")
    public R login(@RequestBody Student student){
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.eq("stu_numb",student.getStu_numb())
               .eq("password",student.getPassword());
        Student one = stuService.getOne(wrapper);
        if (one==null){
            return new R(false,"用户名或密码错误!",null);
        }else {
            return new R(true,"登录成功!",null);
        }
    }

    @GetMapping("/userinfo/{stu_numb}")
    public R getUserInfo(@PathVariable Integer stu_numb){
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.eq("stu_numb",stu_numb);
        Student one = stuService.getOne(wrapper);
        if (one==null){
            return new R(false,"查询失败!",null);
        }else {
            one.setPassword(null);
            QueryWrapper<StudentDetail> wrapper2 = new QueryWrapper<>();
            wrapper2.eq("ins_numb",one.getIns_numb())
                    .eq("stu_id",one.getStu_id());
            StudentDetail two = stuDeService.getOne(wrapper2);
            Stu stu = new Stu();
            stu.setStudent(one);
            stu.setStudentDetail(two);
            return new R(true,"查询成功!",stu);
        }
    }

    @PutMapping("/userinfo")
    public R updateUserInfo(@RequestBody Student student){
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.eq("stu_numb",student.getStu_numb());
        student.setStu_numb(null);
        boolean b = stuService.update(student,wrapper);
        if (b){
            return new R(true,"修改成功!",null);
        }else {
            return new R(false,"修改失败!",null);
        }
    }

    @PutMapping("/userPassword")
    public R updatePassword(@RequestBody Password password){
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.eq("stu_numb",password.getStu_numb())
               .eq("password",password.getPassword());
        Student student = new Student();
        student.setPassword(password.getNewPassword());
        boolean b = stuService.update(student,wrapper);
        if (b){
            return new R(true,"修改成功!",null);
        }else {
            return new R(false,"原密码不正确!",null);
        }
    }

    @PutMapping("/userFindPd")
    public R findPassword(@RequestBody Password password){
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.eq("stu_numb",password.getStu_numb())
                .eq("email",password.getEmail());
        Student one = stuService.getOne(wrapper);
        if (one!=null){
            emailUtils emailUtils = new emailUtils(password.getEmail());
            emailUtils.run();
            return new R(true,"请查看邮箱信息!",null);
        }else {
            return new R(false,"邮箱不正确!",null);
        }
    }

    @PutMapping("/changePhoto")
    public R changePhoto(@RequestBody Password password, HttpServletRequest request) throws IOException {
        String basePath="/upload/";
        String filePathName= request.getSession().getServletContext().getRealPath(basePath);

//        .transferTo(new File(filePathName+"\\"+password.getNick_photo()));
        String path1="http://localhost:8099";
        String path2=request.getContextPath();
        String path3=path1+path2+basePath+password.getNick_photo();

        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.eq("stu_numb",password.getStu_numb());
        Student student = new Student();
        student.setNick_photo(path3);
        boolean b = stuService.update(student, wrapper);
        if (b){
            return new R(true,"修改成功!",null);
        }else {
            return new R(false,"修改失败!",null);
        }
    }

    @GetMapping("/course/{stu_numb}")
    public R getCourse(@PathVariable Long stu_numb){
        List<Course> courseList = courseService.getCourseInfo(stu_numb);
        return new R(true,"查询成功!",courseList);
    }

    @GetMapping("/message/{stu_numb}")
    public R getMsg(@PathVariable Integer stu_numb){
        List<Message> list = messageService.getMessage(stu_numb);
        return new R(true,"查询成功!",list);
    }

    @DeleteMapping("/message/{msgId}")
    public R delMsg(@PathVariable Integer msgId){
        boolean b = messageService.delById(msgId);
        if (b){
            return new R(true,"删除成功!",null);
        }else {
            return new R(false,"删除失败!",null);
        }
    }

    @DeleteMapping("/delAllMessage/{stu_numb}")
    public R delAllMsg(@PathVariable Integer stu_numb){
        boolean b = messageService.delAll(stu_numb);
        if (b){
            return new R(true,"删除成功!",null);
        }else {
            return new R(false,"删除失败!",null);
        }
    }

    @PostMapping("/findPassword")
    public R findPd(@RequestBody Password password){
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.eq("stu_numb",password.getStu_numb());
        Student student = new Student();
        student.setPassword(password.getPassword());
        boolean one = stuService.update(student,wrapper);
        if (!one){
            return new R(false,"密码错误!",null);
        }else {
            return new R(true,"找回成功!",null);
        }
    }


}
