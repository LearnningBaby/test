package com.student.studentservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.student.studentservice.domain.*;
import com.student.studentservice.service.Impl.FaceServiceImpl;
import com.student.studentservice.service.Impl.StuServiceImpl;
import com.student.studentservice.service.Impl.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/student")
public class TestController {
    @Autowired
    private TestServiceImpl testService;

    @Autowired
    private StuServiceImpl stuService;

    @Autowired
    private FaceServiceImpl faceService;

    @GetMapping("/test/{stu_numb}")
    public R getTest(@PathVariable Long stu_numb){
        List<TestAll> test = testService.getTest(stu_numb);
        return new R(true,"查询成功!",test);
    }

    @GetMapping("/confirm/{testNumb}/{crsNumb}")
    public R confirm(@PathVariable Long testNumb,@PathVariable Long crsNumb){
        Confirm confirm = testService.getConfirm(testNumb, crsNumb);
        return new R(true,"查询成功!",confirm);
    }

    @GetMapping("/exam/{testNumb}")
    public R getExam(@PathVariable Long testNumb){
        QueryWrapper<Test> wrapper = new QueryWrapper<>();
        wrapper.eq("test_numb",testNumb);
        Test one = testService.getOne(wrapper);
        ExamPaperAll body = testService.getExamBody(one.getExamNumb());
        return new R(true,"查询成功!",body);
    }

    @GetMapping("/getTestPass/{stu_numb}")
    public R getTestPass(@PathVariable Long stu_numb){
        List<TestAll> test = testService.getTestPass(stu_numb);
        return new R(true,"查询成功!",test);
    }

    @PostMapping("/exam")
    public R submitExam(@RequestBody List<String> list){
        testService.submitExam(list);
        return new R(true,"提交成功!",null);
    }

    @PostMapping("/face")
    public R face(@RequestBody Face face){
        testService.addHead(face.getImage(),face.getStuNumb());
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.eq("stu_numb",face.getStuNumb());
        Student one = stuService.getOne(wrapper);
        String stuHead = testService.getStuHead(one);
        boolean b = faceService.isSameByBase64(face.getImage(), stuHead.substring(23));
        if (b){
            return new R(true,"",null);
        }else {
            return new R(false,"",null);
        }
    }

}
