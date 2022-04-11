package com.teacher.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.teacher.domain.Class;
import com.teacher.domain.*;
import com.teacher.service.Impl.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@RestController
@RequestMapping("/teacher")
public class TestController {

    @Autowired
    private TestServiceImpl testService;

    @PostMapping("/createTest")
    public R createTest(@RequestBody Test test){
        Long clsNumb = test.getClsNumb();
        String s = clsNumb.toString();
        String[] split = s.split("0");
        Integer clsQutity = testService.getClsQutity(split);
        test.setSumPeople(clsQutity);
        test.setTestStatus(0);
        testService.save(test);
        QueryWrapper<Test> wrapper = new QueryWrapper<>();
        wrapper.eq("test_name",test.getTestName());
        Test one = testService.getOne(wrapper);
        Date date = new Date();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                QueryWrapper<Test> wrapper = new QueryWrapper<>();
                wrapper.eq("test_numb",one.getTestNumb());
                Test test = new Test();
                test.setTestStatus(1);
                testService.update(test,wrapper);
            }
        }, test.getStartTime().getTime() - date.getTime());
        timer.schedule(new TimerTask() {
            public void run() {
                QueryWrapper<Test> wrapper = new QueryWrapper<>();
                wrapper.eq("test_numb",one.getTestNumb());
                Test test = new Test();
                test.setTestStatus(2);
                testService.update(test,wrapper);
            }
        }, test.getEndTime().getTime() - date.getTime());
        return new R(true,"创建成功!",null);
    }

    @GetMapping("/test/{teaNumb}/{currentPage}/{pageSize}")
    public R getTest(@PathVariable Long teaNumb,@PathVariable Integer currentPage,@PathVariable Integer pageSize,Test test){
        IPage<Test> page = testService.getByPage(teaNumb, currentPage, pageSize, test);
        if (currentPage>page.getPages()){
            page=testService.getByPage(teaNumb, currentPage, pageSize, test);
        }
        return new R(true,"查询成功!",page);
    }

    @DeleteMapping("/test/{testNumb}")
    public R delTest(@PathVariable Long testNumb){
        QueryWrapper<Test> wrapper = new QueryWrapper<>();
        wrapper.eq("test_numb",testNumb);
        testService.remove(wrapper);
        return new R(true,"",null);
    }

    @PostMapping("/delAllTest")
    public R delAllTest(@RequestBody List<Long> list){
        for (Long aLong : list) {
            QueryWrapper<Test> wrapper = new QueryWrapper<>();
            wrapper.eq("test_numb",aLong);
            testService.remove(wrapper);
        }
        return new R(true,"",null);
    }

    @GetMapping("/getTestClass/{testNumb}")
    public R getTestClass(@PathVariable Long testNumb){
        List<String> clsName = testService.getClsName(testNumb);
        StringBuilder builder = new StringBuilder();
        for (String s : clsName) {
            builder.append(s+",");
        }
        return new R(true,"",builder);
    }

    @PutMapping("/test")
    public R updateTest(@RequestBody Test test){
        QueryWrapper<Test> wrapper = new QueryWrapper<>();
        wrapper.eq("test_numb",test.getTestNumb());
        testService.update(test,wrapper);
        return new R(true,"",null);
    }

    @GetMapping("/getTestInfo/{teaNumb}/{testStatus}")
    public R getTestInfo(@PathVariable Long teaNumb,@PathVariable Integer testStatus){
        QueryWrapper<Test> wrapper = new QueryWrapper<>();
        wrapper.eq("tea_numb",teaNumb);
        wrapper.eq("test_status",testStatus);
        List<Test> list = testService.list(wrapper);
        return new R(true,"",list);
    }

    @GetMapping("/getTestClassInfo/{testNumb}")
    public R getTestClassInfo(@PathVariable Long testNumb){
        List<Class> classList = testService.getClsInfo(testNumb);
        return new R(true,"",classList);
    }

    @GetMapping("/getTestClassStudent/{clsNumb}")
    public R getTestClassStudent(@PathVariable Long clsNumb){
        TestStu student = testService.getStudent(clsNumb);
        return new R(true,"",student);
    }

    @PostMapping("/resetTest")
    public R resetTest(@RequestBody TestStudent testStudent){
         testService.resetTest(testStudent,2);
         return new R(true,"设置重考成功!",null);
    }

    @PostMapping("/resetTest2")
    public R resetTest2(@RequestBody TestStudent testStudent){
        testService.resetTest(testStudent,0);
        return new R(true,"",null);
    }

    @PostMapping("/resetTest3")
    public R resetTest3(@RequestBody TestStudent testStudent){
        testService.resetTest(testStudent,-1);
        return new R(true,"",null);
    }

    @GetMapping("/getTestInfo/{teaNumb}/{testStatus}/{checkMethod}")
    public R getTestInfo2(@PathVariable Long teaNumb,@PathVariable Integer testStatus,@PathVariable Integer checkMethod){
        QueryWrapper<Test> wrapper = new QueryWrapper<>();
        wrapper.eq("tea_numb",teaNumb);
        wrapper.eq("test_status",testStatus);
        wrapper.eq("check_method",checkMethod);
        List<Test> list = testService.list(wrapper);
        return new R(true,"",list);
    }

    @GetMapping("/exam/{testNumb}")
    public R getExam(@PathVariable Long testNumb){
        QueryWrapper<Test> wrapper = new QueryWrapper<>();
        wrapper.eq("test_numb",testNumb);
        Test one = testService.getOne(wrapper);
        ExamPaperAll body = testService.getExamBody(one.getExamNumb());
        return new R(true,"查询成功!",body);
    }

    @GetMapping("/getStuAnswer/{testNumb}")
    public R getStuAnswer(@PathVariable Long testNumb){
        StuAnswer answer = testService.getStuAnswer(testNumb);
        return new R(true,"查询成功!",answer);
    }

    @PostMapping("/submitStuScore")
    public R submitStuScore(@RequestBody StuRes stuRes){
        testService.submitStuScore(stuRes);
        return new R(true,"提交成功!",null);
    }


}
