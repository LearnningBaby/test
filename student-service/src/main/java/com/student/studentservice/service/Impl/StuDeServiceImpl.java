package com.student.studentservice.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.student.studentservice.domain.StudentDetail;
import com.student.studentservice.mapper.StuDeMapper;
import com.student.studentservice.service.StuDeService;
import org.springframework.stereotype.Service;

@Service
public class StuDeServiceImpl extends ServiceImpl<StuDeMapper, StudentDetail> implements StuDeService {
}
