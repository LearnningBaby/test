package com.admin.service.impl;

import com.admin.domain.Student;
import com.admin.mapper.StuMapper;
import com.admin.service.StuService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StuServiceImpl extends ServiceImpl<StuMapper, Student> implements StuService {

    @Resource
    private StuMapper stuMapper;

    @Override
    public IPage<Student> getByPage(Integer currentPage, Integer pageSize, Student student) {
        Page<Student> page = new Page<>(currentPage,pageSize);
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(student.getOnlineStatus()!=null,Student::getOnlineStatus,student.getOnlineStatus());
        wrapper.like(Strings.isNotEmpty(student.getNickName()),Student::getNickName,student.getNickName());
        return stuMapper.selectPage(page,wrapper);
    }
}
