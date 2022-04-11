package com.teacher.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.teacher.domain.*;
import com.teacher.domain.Class;

import java.util.List;

public interface TestService extends IService<Test> {
    Integer getClsQutity(String[] split);

    IPage<Test> getByPage(Long teaNumb, Integer currentPage, Integer pageSize, Test test);

    List<String> getClsName(Long testNumb);

    List<Class> getClsInfo(Long testNumb);

    TestStu getStudent(Long clsNumb);

    void resetTest(TestStudent testStudent,Integer integer);

    ExamPaperAll getExamBody(Long examNumb);

    StuAnswer getStuAnswer(Long testNumb);

    void submitStuScore(StuRes stuRes);
}
