package com.teacher.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.teacher.domain.Exam;
import com.teacher.domain.ExamAll;
import com.teacher.domain.ExamMy;
import com.teacher.domain.R;
import com.teacher.service.Impl.ExamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class ExamController {

    @Autowired
    private ExamServiceImpl examService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("/autoExam")
    public R autoTest(@RequestBody ExamAll examAll){
         examService.autoExam(examAll);
         return new R(true,"组卷成功!",null);
    }

    @PostMapping("/manualExam")
    public R myTest(@RequestBody ExamMy examMy){
        examService.manualExam(examMy);
        return new R(true,"组卷成功!",null);
    }

    @GetMapping("/exam/{teaNumb}/{currentPage}/{pageSize}")
    public R getExam(@PathVariable Long teaNumb, @PathVariable Integer currentPage, @PathVariable Integer pageSize, Exam exam){
        IPage<Exam> page = examService.getByPage(teaNumb, currentPage, pageSize, exam);
        if (currentPage>page.getPages()){
            page = examService.getByPage(teaNumb, currentPage, pageSize, exam);
        }
        return new R(true,"查询成功!",page);
    }

    @DeleteMapping("/exam/{examNumb}")
    public R delExam(@PathVariable Long examNumb){
        QueryWrapper<Exam> wrapper = new QueryWrapper<>();
        wrapper.eq("exam_numb",examNumb);
        examService.remove(wrapper);
        redisTemplate.opsForList().getOperations().delete("exam:"+examNumb);
        return new R(true,"删除成功!",null);
    }

    @PostMapping("/delAllExam")
    public R delAllExam(@RequestBody Long[] examNumb){
        for (Long aLong : examNumb) {
            QueryWrapper<Exam> wrapper = new QueryWrapper<>();
            wrapper.eq("exam_numb",aLong);
            examService.remove(wrapper);
            redisTemplate.opsForList().getOperations().delete("exam:"+aLong);
        }
        return new R(true,"删除成功!",null);
    }

    @GetMapping("/getExamInfo/{teaNumb}")
    public R getExamInfo(@PathVariable Long teaNumb){
        QueryWrapper<Exam> wrapper = new QueryWrapper<>();
        wrapper.eq("tea_numb",teaNumb);
        List<Exam> list = examService.list(wrapper);
        return new R(true,"查询成功!",list);
    }
}
