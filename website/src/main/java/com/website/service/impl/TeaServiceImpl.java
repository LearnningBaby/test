package com.website.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.website.damian.Teacher;
import com.website.damian.TeacherAll;
import com.website.mapper.StuMapper;
import com.website.mapper.TeaMapper;
import com.website.service.TeaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.website.config.constant.NICK_PHOTO;

@Service
public class TeaServiceImpl extends ServiceImpl<TeaMapper, Teacher> implements TeaService {

    @Resource
    private TeaMapper teaMapper;

    @Resource
    private StuMapper stuMapper;
    @Override
    public boolean teaRegister(TeacherAll teacher) {
        Long insNumb = stuMapper.getInsNumb(teacher.getInsName());
        Teacher teacher1 = new Teacher();
        teacher1.setInsNumb(insNumb);
        teacher1.setTeaWno(teacher.getTeaWno());
        teaMapper.addTea(teacher1);
        teacher1.setEmail(teacher.getEmail());
        teacher1.setNickPhoto(NICK_PHOTO);
        teacher1.setPassword(teacher.getPassword());
        teacher1.setOnlineStatus(1);
        return teaMapper.insert(teacher1)>0;
    }

    @Override
    public boolean teaLogin(TeacherAll teacher) {
        Long insNumb = stuMapper.getInsNumb(teacher.getInsName());
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        //wrapper.eq("ins_numb",insNumb);
        wrapper.eq("tea_wno",teacher.getTeaWno());
        wrapper.eq("password",teacher.getPassword());
        Teacher one = teaMapper.selectOne(wrapper);
        return one!=null ? true:false;
    }
}
