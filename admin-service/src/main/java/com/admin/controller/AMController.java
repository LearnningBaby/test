package com.admin.controller;

import com.admin.domain.Admin;
import com.admin.domain.R;
import com.admin.filter.BaseResponse;
import com.admin.service.impl.AdminServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@BaseResponse
@RestController
//@RequestMapping("/admin")
public class AMController {

    @Autowired
    private AdminServiceImpl adminService;

    @GetMapping("/am/{currentPage}/{pageSize}")
    public IPage<Admin> getIns(@PathVariable Integer currentPage, @PathVariable Integer pageSize, Admin admin){
        IPage<Admin> page = adminService.getByPage(currentPage, pageSize, admin);
        if (currentPage>page.getPages()){
            page = adminService.getByPage(currentPage, pageSize, admin);
        }
        return page;
    }

    @PutMapping("/am")
    public R updateInstitution(@RequestBody Admin admin){
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("admin_numb",admin.getAdminNumb());
        admin.setAdminNumb(null);
        adminService.update(admin,wrapper);
        return new R(true,"修改成功!",null);
    }

    @DeleteMapping("/am/{adminNumb}")
    public R delInstitution(@PathVariable Long adminNumb){
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("admin_numb",adminNumb);
        adminService.remove(wrapper);
        return new R(true,"删除成功!",null);
    }

    @PostMapping("/delAllAdmin")
    public R delAllAdmin(@RequestBody List<Long> list){
        for (Long aLong : list) {
            QueryWrapper<Admin> wrapper = new QueryWrapper<>();
            wrapper.eq("admin_numb",aLong);
            adminService.remove(wrapper);
        }
        return new R(true,"删除成功!",null);
    }

    @PostMapping("/am")
    public R addAdmin(@RequestBody Admin admin){
        adminService.save(admin);
        return new R(true,"添加成功!",null);
    }
}
