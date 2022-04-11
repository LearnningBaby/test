package com.teacher.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.teacher.domain.*;
import com.teacher.domain.Class;
import com.teacher.service.Impl.StuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class ClassController {

    @Autowired
    private StuServiceImpl stuService;

    @GetMapping("/class/{teaNumb}")
    public R getClass(@PathVariable Long teaNumb){
        List<Class> classList = stuService.getClassInfo(teaNumb);
        return new R(true,"查询成功!",classList);
    }


    @GetMapping("/student/{clsNumb}/{currentPage}/{pageSize}")
    public R getStudentByClass(@PathVariable Long clsNumb, @PathVariable Long currentPage, @PathVariable Long pageSize, StudentDetail student){
        IPage<StudentDetail> page = stuService.getStuInfo(clsNumb, currentPage, pageSize, student);
        if (currentPage>page.getPages()){
            page=stuService.getStuInfo(clsNumb,currentPage,pageSize,student);
        }
        return new R(true,"查询成功!",page);
    }

    @PutMapping("/student")
    public R updateStu(@RequestBody StudentDetail studentDetail){
        QueryWrapper<StudentDetail> wrapper = new QueryWrapper<>();
        wrapper.eq("ins_numb",studentDetail.getInsNumb())
                .eq("stu_id",studentDetail.getStuId());
        studentDetail.setInsNumb(null);
        boolean b = stuService.update(studentDetail, wrapper);
        if (b){
            return new R(true,"修改成功!",null);
        }else {
            return new R(false,"修改失败!",null);
        }
    }

    @GetMapping("/getUnitInfo")
    public R getIns(){
        List<Ins> unitInfo = stuService.getUnitInfo();
        return new R(true,"查询成功!",unitInfo);
    }

    @PostMapping("/student")
    public R addStu(@RequestBody Student student){
        boolean b = stuService.addStu(student);
        if (b){
            return new R(true,"新增成功!",null);
        }else {
            return new R(false,"新增失败!",null);
        }
    }

    @DeleteMapping("/student/{insNumb}/{stuId}")
    public R delStu(@PathVariable Long insNumb,@PathVariable Long stuId){
        boolean b = stuService.delStu(insNumb, stuId);
        if (b){
            return new R(true,"删除成功!",null);
        }else {
            return new R(false,"删除失败!",null);
        }
    }

    @PostMapping("/deleteAllStuInfo")
    public R delAllStu(@RequestBody List<StudentDetail> studentDetailList){
        for (StudentDetail detail : studentDetailList) {
            boolean b = stuService.delStu(detail.getInsNumb(), detail.getStuId());
        }
        return new R(true,"删除成功!",null);
    }

    @PostMapping("/bulk")
    public R bulkImport(@RequestBody Student student){
        boolean b = stuService.bulkImport(student);
        if (b){
            return new R(true,"批量导入成功!",null);
        }else {
            return new R(false,"批量导入失败!",null);
        }
    }


}
