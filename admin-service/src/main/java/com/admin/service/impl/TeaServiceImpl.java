package com.admin.service.impl;

import com.admin.domain.Teacher;
import com.admin.mapper.TeaMapper;
import com.admin.service.TeaService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TeaServiceImpl extends ServiceImpl<TeaMapper, Teacher> implements TeaService {

    @Resource
    private TeaMapper teaMapper;

    @Override
    public IPage<Teacher> getByPage(Integer currentPage, Integer pageSize, Teacher teacher) {
        Page<Teacher> page = new Page<>(currentPage,pageSize);
        LambdaQueryWrapper<Teacher> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(teacher.getOnlineStatus()!=null,Teacher::getOnlineStatus,teacher.getOnlineStatus());
        wrapper.eq(teacher.getTeaWno()!=null,Teacher::getTeaWno,teacher.getTeaWno());
        return teaMapper.selectPage(page,wrapper);
    }
}
