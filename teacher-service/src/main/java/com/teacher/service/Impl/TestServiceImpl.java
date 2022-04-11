package com.teacher.service.Impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teacher.domain.Class;
import com.teacher.domain.*;
import com.teacher.mapper.ResMapper;
import com.teacher.mapper.TestMapper;
import com.teacher.service.TestService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {

    @Resource
    private TestMapper testMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Resource
    private ResMapper resMapper;

    @Override
    public Integer getClsQutity(String[] split) {
        Integer sum=0;
        for (String s : split) {
            Integer qutity = testMapper.getClsQutity(Long.parseLong(s));
            sum+=qutity;
        }
        return sum;
    }

    @Override
    public IPage<Test> getByPage(Long teaNumb, Integer currentPage, Integer pageSize, Test test) {
        Page<Test> page = new Page<>(currentPage,pageSize);
        LambdaQueryWrapper<Test> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Strings.isNotEmpty(test.getTestName()),Test::getTestName,test.getTestName());
        wrapper.eq(test.getCheckMethod()!=null,Test::getCheckMethod,test.getCheckMethod());
        wrapper.eq(true,Test::getTeaNumb,teaNumb);
        return testMapper.selectPage(page, wrapper);
    }

    @Override
    public List<String> getClsName(Long testNumb) {
        Long clsNumb = testMapper.getClsNumb(testNumb);
        List<String> list = new ArrayList<>();
        if (clsNumb>100){
            String s = clsNumb.toString();
            String[] split = s.split("0");
            for (String s1 : split) {
                String clsName = testMapper.getClsName(Long.parseLong(s1));
                list.add(clsName);
            }
            return list;
        }else {
            list.add(testMapper.getClsName(clsNumb));
            return list;
        }
    }

    @Override
    public List<Class> getClsInfo(Long testNumb) {
        Long clsNumb = testMapper.getClsNumb(testNumb);
        List<Class> list = new ArrayList<>();
        if (clsNumb>100){
            String s = clsNumb.toString();
            String[] split = s.split("0");
            for (String s1 : split) {
                Class clsName = testMapper.getClsInfo(Long.parseLong(s1));
                list.add(clsName);
            }
            return list;
        }else {
            list.add(testMapper.getClsInfo(clsNumb));
            return list;
        }
    }

    @Override
    public TestStu getStudent(Long clsNumb) {
        String clsName = testMapper.getClsName(clsNumb);
        List<StudentDetail> detailList = testMapper.getStu(clsName);
        TestStu testStu = new TestStu();
        testStu.setTotalNumber((long) detailList.size());
        List<TestStudent> stuList = new ArrayList<>();
        for (StudentDetail student : detailList) {
            Long stuNumb = testMapper.getStuNumb(student.getInsNumb(), student.getStuId());
            TestStudent status = testMapper.getStuStatus(stuNumb);
            status.setClsName(student.getClsName());
            status.setStuId(student.getStuId());
            status.setStuName(student.getStuName());
            stuList.add(status);
        }
        testStu.setTestStudent(stuList);
        testStu.setTestNumber(testMapper.getTestCount(clsNumb,1));
        testStu.setSubmitNumber(testMapper.getTestCount(clsNumb,0));
        testStu.setMissNumber(testMapper.getTestCount(clsNumb,2));
        return testStu;
    }

    @Override
    public void resetTest(TestStudent testStudent,Integer integer) {
        Long insNumb = testMapper.getInsNumb(testStudent.getStuId(), testStudent.getStuName());
        Long stuNumb = testMapper.getStuNumb(insNumb, testStudent.getStuId());
        testMapper.resetTest(stuNumb,integer);
    }

    @Override
    public ExamPaperAll getExamBody(Long examNumb) {
        Exam exam = testMapper.getExam(examNumb);
        String s = redisTemplate.opsForValue().get(exam.getBody());
        ExamPaper paper = JSON.parseObject(s, ExamPaper.class);
        ExamPaperAll all = new ExamPaperAll();
        List<Object> objects = new ArrayList<>();
        for (Select select : paper.getSelectList()) {
            objects.add(select);
        }
        for (Blank blank : paper.getBlankList()) {
            objects.add(blank);
        }
        for (ShortAnswer answer : paper.getAnswerList()) {
            objects.add(answer);
        }
        all.setPaperList(objects);
        List<TitleInfo> titleInfos = new ArrayList<>();
        TitleInfo titleInfo = new TitleInfo();
        titleInfo.setIndex(0);
        titleInfo.setNum(paper.getSelectList().size());
        titleInfo.setTime(5);
        titleInfo.setType(1);
        titleInfos.add(titleInfo);
        TitleInfo titleInfo2 = new TitleInfo();
        titleInfo2.setIndex(1);
        titleInfo2.setNum(paper.getBlankList().size());
        titleInfo2.setTime(5);
        titleInfo2.setType(2);
        titleInfos.add(titleInfo2);
        TitleInfo titleInfo3 = new TitleInfo();
        titleInfo3.setIndex(2);
        titleInfo3.setNum(paper.getAnswerList().size());
        titleInfo3.setTime(10);
        titleInfo3.setType(3);
        titleInfos.add(titleInfo3);
        all.setInfoList(titleInfos);
        return all;
    }

    @Override
    public StuAnswer getStuAnswer(Long testNumb) {
        Long examNumb = testMapper.getExamNumb(testNumb);
        List<Long> stuNumbList = testMapper.getStuNumbList(examNumb);
        List<List<String>> lists = new ArrayList<>();
        StuAnswer answer = new StuAnswer();
        answer.setNumber(stuNumbList.size());
        for (Long aLong : stuNumbList) {
            String s = redisTemplate.opsForValue().get("testNumb:" + testNumb + ":exam:" + examNumb + ":stuNumb:" + aLong);
            List<String> list = JSON.parseArray(s, String.class);
            lists.add(list);
        }
        answer.setAnswer(lists);
        return answer;
    }

    @Override
    public void submitStuScore(StuRes stuRes) {
        resMapper.insert(stuRes);
    }


}
