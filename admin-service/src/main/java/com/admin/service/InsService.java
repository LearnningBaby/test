package com.admin.service;

import com.admin.domain.Ins;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface InsService extends IService<Ins> {
    IPage<Ins> getByPage(Integer currentPage, Integer pageSize, Ins ins);
}
