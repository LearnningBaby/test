package com.website.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.website.damian.Teacher;
import com.website.damian.TeacherAll;

public interface TeaService extends IService<Teacher> {
    boolean teaRegister(TeacherAll teacher);

    boolean teaLogin(TeacherAll teacher);
}
