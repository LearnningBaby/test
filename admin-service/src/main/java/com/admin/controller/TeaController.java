package com.admin.controller;

import com.admin.domain.R;
import com.admin.domain.Teacher;
import com.admin.filter.BaseResponse;
import com.admin.service.impl.TeaServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@BaseResponse
@RestController
//@RequestMapping("/admin")
public class TeaController {

    @Autowired
    private TeaServiceImpl teaService;



    @GetMapping("/teacher/{currentPage}/{pageSize}")
    public IPage<Teacher> getStu(@PathVariable Integer currentPage, @PathVariable Integer pageSize, Teacher teacher){
        IPage<Teacher> page = teaService.getByPage(currentPage, pageSize, teacher);
        if (currentPage>page.getPages()){
            page = teaService.getByPage(currentPage, pageSize, teacher);
        }
        return page;
    }

    @PutMapping("/teacher")
    public R updateStu(@RequestBody Teacher teacher){
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        wrapper.eq("tea_numb",teacher.getTeaNumb());
        teacher.setTeaNumb(null);
        teaService.update(teacher,wrapper);
        return new R(true,"修改成功!",null);
    }

    @DeleteMapping("/teacher/{teaNumb}")
    public R delStu(@PathVariable Long teaNumb){
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        wrapper.eq("tea_numb",teaNumb);
        teaService.remove(wrapper);
        return new R(true,"删除成功!",null);
    }

    @PostMapping("/delAllTea")
    public R delAllStu(@RequestBody List<Long> list){
        for (Long aLong : list) {
            QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
            wrapper.eq("tea_numb",aLong);
            teaService.remove(wrapper);
        }
        return new R(true,"删除成功!",null);
    }

    @PostMapping("/teacher")
    public R addAdmin(@RequestBody Teacher teacher){
        teaService.save(teacher);
        return new R(true,"添加成功!",null);
    }

  
}
