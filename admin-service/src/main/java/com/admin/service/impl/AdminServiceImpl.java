package com.admin.service.impl;

import com.admin.domain.Admin;
import com.admin.mapper.AdminMapper;
import com.admin.service.AdminService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Resource
    private AdminMapper adminMapper;

    @Override
    public IPage<Admin> getByPage(Integer currentPage, Integer pageSize, Admin admin) {
        Page<Admin> page = new Page<>(currentPage,pageSize);
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(admin.getOnlineStatus()!=null,Admin::getOnlineStatus,admin.getOnlineStatus());
        wrapper.eq(admin.getAdminNumb()!=null,Admin::getAdminNumb,admin.getAdminNumb());
        return adminMapper.selectPage(page,wrapper);
    }
}
