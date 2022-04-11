package com.student.studentservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.student.studentservice.domain.*;

import java.util.List;

public interface TestService extends IService<Test> {
    List<TestAll> getTest(Long stu_numb);

    Confirm getConfirm(Long testNumb, Long crsNumb);

    ExamPaperAll getExamBody(Long examNumb);

    List<TestAll> getTestPass(Long stu_numb);

    void submitExam(List<String> list);

    String getStuHead(Student one);

    void addHead(String image,Long stuNumb);

}
