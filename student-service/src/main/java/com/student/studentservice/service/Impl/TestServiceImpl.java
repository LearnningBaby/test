package com.student.studentservice.service.Impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.student.studentservice.domain.*;
import com.student.studentservice.mapper.ResMapper;
import com.student.studentservice.mapper.TestMapper;
import com.student.studentservice.service.TestService;
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

    /**
     * 查询考试信息
     * @param stu_numb
     * @return
     */
    @Override
    public List<TestAll> getTest(Long stu_numb) {
        List<Long> crsNumb = testMapper.getCrsNumb(stu_numb);
        if (crsNumb!=null){
            ArrayList<Test> tests = new ArrayList<>();
            for (Long aLong : crsNumb) {
                QueryWrapper<Test> wrapper = new QueryWrapper<>();
                wrapper.eq("crs_numb",aLong);
                wrapper.lt("test_status",2);
                List<Test> test = testMapper.selectList(wrapper);
                tests.addAll(test);
            }
            if (!tests.isEmpty()){
                ArrayList<TestAll> testAlls = new ArrayList<>();
                for (Test test : tests) {
                    Course course = testMapper.getCrsName(test.getCrsNumb());
                    Exam exam = testMapper.getExam(test.getExamNumb());
                    TestAll testAll = new TestAll();
                    testAll.setTestNumb(test.getTestNumb());
                    testAll.setCrsName(course.getCrsName());
                    testAll.setCrsNumb(test.getCrsNumb());
                    testAll.setSumScore(exam.getSumScore());
                    testAll.setTestName(test.getTestName());
                    testAll.setEndTime(test.getEndTime());
                    testAll.setStartTime(test.getStartTime());
                    testAll.setNeedTime(test.getNeedTime());
                    testAlls.add(testAll);
                }
                return testAlls;
            }
        }
        return null;
    }

    @Override
    public Confirm getConfirm(Long testNumb, Long crsNumb) {
        Course course = testMapper.getCrsName(crsNumb);
        Exam exam = testMapper.getExamName(crsNumb);
        QueryWrapper<Test> wrapper = new QueryWrapper<>();
        wrapper.eq("test_numb",testNumb);
        Test test = testMapper.selectOne(wrapper);
        Confirm confirm = new Confirm();
        confirm.setCrsName(course.getCrsName());
        confirm.setSumScore(exam.getSumScore());
        confirm.setStartTime(test.getStartTime());
        confirm.setEndTime(test.getEndTime());
        return confirm;
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
    public List<TestAll> getTestPass(Long stu_numb) {
        List<Long> crsNumb = testMapper.getCrsNumb(stu_numb);
        if (crsNumb!=null){
            ArrayList<Test> tests = new ArrayList<>();
            for (Long aLong : crsNumb) {
                QueryWrapper<Test> wrapper = new QueryWrapper<>();
                wrapper.eq("crs_numb",aLong);
                wrapper.eq("test_status",2);
                List<Test> test = testMapper.selectList(wrapper);
                tests.addAll(test);
            }
            if (!tests.isEmpty()){
                ArrayList<TestAll> testAlls = new ArrayList<>();
                for (Test test : tests) {
                    Course course = testMapper.getCrsName(test.getCrsNumb());
                    Exam exam = testMapper.getExam(test.getExamNumb());
                    TestAll testAll = new TestAll();
                    testAll.setTestNumb(test.getTestNumb());
                    testAll.setCrsName(course.getCrsName());
                    testAll.setCrsNumb(test.getCrsNumb());
                    testAll.setSumScore(exam.getSumScore());
                    testAll.setTestName(test.getTestName());
                    testAll.setEndTime(test.getEndTime());
                    testAll.setStartTime(test.getStartTime());
                    testAll.setNeedTime(test.getNeedTime());
                    testAlls.add(testAll);
                }
                return testAlls;
            }
        }
        return null;
    }

    /**
     * 提交学生答案
     * @param list
     */
    @Override
    public void submitExam(List<String> list) {
        Long examNumb = testMapper.getExamNumb(Long.valueOf(list.get(list.size() - 2)));
        Integer checkMethod = testMapper.getCheckMethod(Integer.valueOf(list.get(list.size() - 2)));
        ExamPaperAll body = getExamBody(examNumb);
        List<String> answer = getAnswer(body);//答案
        StuRes stuRes = new StuRes();
        Integer selectScore=0,spaceScore=0,shortScore=0;
        for (int i = 0; i < body.getInfoList().get(0).getNum(); i++) {
            if (list.get(i).equals(answer.get(i))){
                selectScore+=10;
            }
        }
        stuRes.setSelectRes(selectScore);
        stuRes.setStuNumb(Long.valueOf(list.get(list.size()-1)));
        stuRes.setExamNumb(examNumb);
        if (checkMethod==0) {//自动批改
            for (int i = body.getInfoList().get(0).getNum(); i < body.getInfoList().get(1).getNum(); i++) {
                if (list.get(i).length()>=answer.get(i).length()/2){
                    spaceScore+=10;
                }
            }
            for (int i = body.getInfoList().get(0).getNum()+ body.getInfoList().get(1).getNum(); i < body.getInfoList().get(2).getNum(); i++) {
                if (list.get(i).length()>=answer.get(i).length()/2){
                    selectScore+=20;
                }
            }
            stuRes.setSpaceRes(spaceScore);
            stuRes.setShortRes(shortScore);
            stuRes.setGrade(selectScore+spaceScore+shortScore);
        } else {//手动批改
        }
        resMapper.insert(stuRes);
        redisTemplate.opsForValue().set("testNumb:"+Long.valueOf(list.get(list.size()-1))+":exam:"+examNumb+":stuNumb:"+Long.valueOf(list.get(list.size()-1)),JSON.toJSONString(list.subList(0,list.size()-2)));
    }

    @Override
    public String getStuHead(Student one) {
        return testMapper.getStuHead(one.getIns_numb(),one.getStu_id());
    }

    @Override
    public void addHead(String image,Long stuNumb) {
        testMapper.addHead(image,stuNumb);
    }

    public List<String> getAnswer(ExamPaperAll body){
        List<TitleInfo> infoList = body.getInfoList();
        List<Object> paperList = body.getPaperList();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < infoList.get(0).getNum(); i++) {
            Select o = (Select) paperList.get(i);
            list.add(o.getAns());
        }
        for (int i =infoList.get(0).getNum() ; i < infoList.get(1).getNum(); i++) {
            Blank o = (Blank) paperList.get(i);
            list.add(o.getAns().toString());
        }
        for (int i = infoList.get(0).getNum()+infoList.get(1).getNum(); i < infoList.get(2).getNum(); i++) {
            ShortAnswer o = (ShortAnswer) paperList.get(i);
            list.add(o.getAns());
        }
        return list;
    }

}
