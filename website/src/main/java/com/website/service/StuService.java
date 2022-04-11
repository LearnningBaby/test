package com.website.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.website.damian.Student;
import com.website.damian.StudentAll;

public interface StuService extends IService<Student> {
    boolean stuRegister(StudentAll student);

    boolean stuLogin(StudentAll student);
}
