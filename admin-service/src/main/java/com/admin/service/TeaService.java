package com.admin.service;

import com.admin.domain.Teacher;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface TeaService extends IService<Teacher> {
    IPage<Teacher> getByPage(Integer currentPage, Integer pageSize, Teacher teacher);
}
