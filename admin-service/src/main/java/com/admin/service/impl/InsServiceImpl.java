package com.admin.service.impl;

import com.admin.domain.Ins;
import com.admin.domain.R;
import com.admin.mapper.InsMapper;
import com.admin.service.InsService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;

@Service
public class InsServiceImpl extends ServiceImpl<InsMapper, Ins> implements InsService {

    @Resource
    private InsMapper insMapper;

    @Override
    public IPage<Ins> getByPage(Integer currentPage, Integer pageSize, Ins ins) {
        Page<Ins> page = new Page<>(currentPage,pageSize);
        LambdaQueryWrapper<Ins> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ins.getInsStatus()!=null,Ins::getInsStatus,ins.getInsStatus());
        wrapper.like(Strings.isNotEmpty(ins.getInsName()),Ins::getInsName,ins.getInsName());
        Page<Ins> insPage = insMapper.selectPage(page, wrapper);
        return insPage;
    }


}
