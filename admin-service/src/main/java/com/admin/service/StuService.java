package com.admin.service;

import com.admin.domain.Student;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface StuService extends IService<Student> {
    IPage<Student> getByPage(Integer currentPage, Integer pageSize, Student student);
}
