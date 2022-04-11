package com.website.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.website.damian.Student;
import com.website.damian.StudentAll;
import com.website.mapper.StuMapper;
import com.website.service.StuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.website.config.constant.NICK_NAME;
import static com.website.config.constant.NICK_PHOTO;

@Service
public class StuServiceImpl extends ServiceImpl<StuMapper, Student> implements StuService {

    @Resource
    private StuMapper stuMapper;

    @Override
    public boolean stuRegister(StudentAll student) {
        Long insNumb = stuMapper.getInsNumb(student.getInsName());
        Student student1 = new Student();
        student1.setInsNumb(insNumb);
        student1.setStuId(student.getStuId());
        stuMapper.addStuDe(student1);
        student1.setEmail(student.getEmail());
        student1.setPassword(student.getPassword());
        student1.setNickName(NICK_NAME);
        student1.setOnlineStatus(1);
        student1.setNickPhoto(NICK_PHOTO);
        return stuMapper.insert(student1)>0;
    }

    @Override
    public boolean stuLogin(StudentAll student) {
        Long insNumb = stuMapper.getInsNumb(student.getInsName());
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
       // wrapper.eq("ins_numb",insNumb);
        wrapper.eq("stu_id",student.getStuId());
        wrapper.eq("password",student.getPassword());
        Student one = stuMapper.selectOne(wrapper);
        return one != null ? true : false;
    }
}
