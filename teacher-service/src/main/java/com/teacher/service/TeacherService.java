package com.teacher.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.teacher.domain.Tea;
import com.teacher.domain.Teacher;

public interface TeacherService extends IService<Teacher> {
    Tea getUserInfo(Long teaNumb);
}
