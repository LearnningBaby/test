package com.teacher.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teacher.domain.Tea;
import com.teacher.domain.Teacher;
import com.teacher.domain.TeacherDetail;
import com.teacher.mapper.TeacherMapper;
import com.teacher.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Resource
    private TeacherMapper teacherMapper;
    @Override
    public Tea getUserInfo(Long teaNumb) {
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        wrapper.eq("tea_numb",teaNumb);
        Teacher teacher = teacherMapper.selectOne(wrapper);
        Tea tea = new Tea();
        teacher.setPassword(null);
        tea.setTeacher(teacher);
        TeacherDetail teadetail = teacherMapper.getTeadetail(teacher.getInsNumb());
        tea.setTeacherDetail(teadetail);
        return tea;
    }
}
