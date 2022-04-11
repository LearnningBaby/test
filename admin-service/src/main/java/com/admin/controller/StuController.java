package com.admin.controller;

import com.admin.domain.Ins;
import com.admin.domain.R;
import com.admin.domain.Student;
import com.admin.filter.BaseResponse;
import com.admin.service.impl.InsServiceImpl;
import com.admin.service.impl.StuServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@BaseResponse
@RestController
//@RequestMapping("/admin")
public class StuController {

    @Autowired
    private StuServiceImpl stuService;

    @Autowired
    private InsServiceImpl insService;

    @GetMapping("/student/{currentPage}/{pageSize}")
    public IPage<Student> getStu(@PathVariable Integer currentPage, @PathVariable Integer pageSize, Student student){
        IPage<Student> page = stuService.getByPage(currentPage, pageSize, student);
        if (currentPage>page.getPages()){
            page = stuService.getByPage(currentPage, pageSize, student);
        }
        return page;
    }

    @PutMapping("/student")
    public R updateStu(@RequestBody Student student){
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.eq("stu_numb",student.getStuNumb());
        student.setStuNumb(null);
        stuService.update(student,wrapper);
        return new R(true,"修改成功!",null);
    }

    @DeleteMapping("/student/{stuNumb}")
    public R delStu(@PathVariable Long stuNumb){
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.eq("stu_numb",stuNumb);
        stuService.remove(wrapper);
        return new R(true,"删除成功!",null);
    }

    @PostMapping("/delAllStu")
    public R delAllStu(@RequestBody List<Long> list){
        for (Long aLong : list) {
            QueryWrapper<Student> wrapper = new QueryWrapper<>();
            wrapper.eq("stu_numb",aLong);
            stuService.remove(wrapper);
        }
        return new R(true,"删除成功!",null);
    }

    @PostMapping("/student")
    public R addAdmin(@RequestBody Student student){
        stuService.save(student);
        return new R(true,"添加成功!",null);
    }

    @GetMapping("/getIns")
    public List<Ins> getInsInfo(){
        List<Ins> list = insService.list();
        return list;
    }
}
