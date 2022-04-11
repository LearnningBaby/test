package com.admin.service;

import com.admin.domain.Admin;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface AdminService extends IService<Admin> {
    IPage<Admin> getByPage(Integer currentPage, Integer pageSize, Admin admin);
}
