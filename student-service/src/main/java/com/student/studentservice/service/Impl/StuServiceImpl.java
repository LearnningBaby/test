package com.student.studentservice.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.student.studentservice.domain.Student;
import com.student.studentservice.mapper.StuMapper;
import com.student.studentservice.service.StuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StuServiceImpl extends ServiceImpl<StuMapper, Student> implements StuService {

    @Resource
    private StuMapper stuMapper;

}
