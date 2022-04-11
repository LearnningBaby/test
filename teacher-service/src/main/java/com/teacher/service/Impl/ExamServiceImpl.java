package com.teacher.service.Impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teacher.domain.*;
import com.teacher.mapper.ExamMapper;
import com.teacher.service.ExamService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExamServiceImpl extends ServiceImpl<ExamMapper, Exam> implements ExamService {

    @Resource
    private ExamMapper examMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private BankServiceImpl bankService;


    /**
     * 自动组卷
     * @param examAll
     */
    @Override
    public void autoExam(ExamAll examAll) {
        Long bankNumb = examMapper.getBankNumb(examAll.getCrsNumb());
        ExamPaper paper = new ExamPaper();
        Integer sumScore=0;
        Exam exam = new Exam();
        exam.setTeaNumb(examAll.getTeaNumb());
        exam.setCrsNumb(examAll.getCrsNumb());
        exam.setExamName(examAll.getExamName());
        if (examAll.getQuestAnswer()!=null){
            sumScore+=examAll.getQuestAnswer()*20;
            QuList<ShortAnswer> answerList = bankService.getQuShortAnswerList(bankNumb);
            List<ShortAnswer> list = new ArrayList<>();
            for (int i = 0; i < examAll.getQuestAnswer(); i++) {
                list.add(answerList.getList().get((int) (Math.random()*answerList.getNumber())));
            }
            paper.setAnswerList(list);
        }
        if (examAll.getQuestBlanks()!=null){
            sumScore+=examAll.getQuestBlanks()*10;
            QuList<Blank> blankList = bankService.getQuBlankList(bankNumb);
            List<Blank> list = new ArrayList<>();
            for (int i = 0; i < examAll.getQuestBlanks(); i++) {
                list.add(blankList.getList().get((int) (Math.random()*blankList.getNumber())));
            }
            paper.setBlankList(list);
        }
        if (examAll.getQuestSelectSingle()!=null){
            sumScore+=examAll.getQuestSelectSingle()*10;
            QuList<Select> selectList = bankService.getQuSelectList(bankNumb);
            ArrayList<Select> list = new ArrayList<>();
            for (int i = 0; i < examAll.getQuestSelectSingle(); i++) {
                list.add(selectList.getList().get((int) (Math.random()*selectList.getNumber())));
            }
            paper.setSelectList(list);
        }
        exam.setSumScore(sumScore);
        exam.setExamLevel(examAll.getExamLevel());
        exam.setCreateTime(examAll.getCreateTime());
        examMapper.insert(exam);
        Long examNumb = examMapper.getExamNumb(exam.getExamName());
        redisTemplate.opsForValue().set("exam:" + examNumb, JSON.toJSONString(paper));
        Exam exam2=new Exam();
        exam2.setBody("exam:" + examNumb);
        QueryWrapper<Exam> wrapper = new QueryWrapper<>();
        wrapper.eq("exam_numb",examNumb);
        examMapper.update(exam2,wrapper);
    }

    /**
     * 手动组卷
     * @param examMy
     */
    @Override
    public void manualExam(ExamMy examMy) {
        ExamPaper paper = new ExamPaper();
        Exam exam = new Exam();
        exam.setTeaNumb(examMy.getTeaNumb());
        exam.setCrsNumb(examMy.getCrsNumb());
        exam.setExamName(examMy.getExamName());
        exam.setSumScore(examMy.getSumScore());
        exam.setExamLevel(examMy.getExamLevel());
        exam.setCreateTime(examMy.getCreateTime());
        if (examMy.getSingleSelect().length>0){
            QuList<Select> selectList = bankService.getQuSelectList(examMy.getBankNumb());
            ArrayList<Select> list = new ArrayList<>();
            for (int i = 0; i < examMy.getSingleSelect().length; i++) {
                list.add(selectList.getList().get(examMy.getSingleSelect()[i]-1));
                selectList.getList().get(examMy.getSingleSelect()[i]-1).setFreLev(selectList.getList().get(examMy.getSingleSelect()[i]-1).getFreLev()+1);
                redisTemplate.opsForHash().put("bankNumb:" + examMy.getBankNumb() + ":questType:1","questNumb:"+examMy.getSingleSelect()[i],JSON.toJSONString(selectList.getList().get(examMy.getSingleSelect()[i]-1)));
            }
            paper.setSelectList(list);
        }
        if (examMy.getBlanks().length>0){
            QuList<Blank> blankList = bankService.getQuBlankList(examMy.getBankNumb());
            ArrayList<Blank> list = new ArrayList<>();
            for (int i = 0; i < examMy.getBlanks().length; i++) {
                list.add(blankList.getList().get(examMy.getBlanks()[i]-1));
                blankList.getList().get(examMy.getBlanks()[i]-1).setFreLev(blankList.getList().get(examMy.getBlanks()[i]-1).getFreLev()+1);
                redisTemplate.opsForHash().put("bankNumb:" + examMy.getBankNumb() + ":questType:2","questNumb:"+examMy.getBlanks()[i],JSON.toJSONString(blankList.getList().get(examMy.getBlanks()[i]-1)));
            }
            paper.setBlankList(list);
        }
        if (examMy.getAnswer().length>0){
            QuList<ShortAnswer> answerList = bankService.getQuShortAnswerList(examMy.getBankNumb());
            ArrayList<ShortAnswer> list = new ArrayList<>();
            for (int i = 0; i < examMy.getAnswer().length; i++) {
                list.add(answerList.getList().get(examMy.getAnswer()[i]-1));
                answerList.getList().get(examMy.getAnswer()[i]-1).setFreLev(answerList.getList().get(examMy.getAnswer()[i]-1).getFreLev()+1);
                redisTemplate.opsForHash().put("bankNumb:" + examMy.getBankNumb() + ":questType:3","questNumb:"+examMy.getAnswer()[i],JSON.toJSONString(answerList.getList().get(examMy.getAnswer()[i]-1)));
            }
            paper.setAnswerList(list);
        }
        examMapper.insert(exam);
        Long examNumb = examMapper.getExamNumb(exam.getExamName());
        redisTemplate.opsForValue().set("exam:" + examNumb, JSON.toJSONString(paper));
        Exam exam2=new Exam();
        exam2.setBody("exam:" + examNumb);
        QueryWrapper<Exam> wrapper = new QueryWrapper<>();
        wrapper.eq("exam_numb",examNumb);
        examMapper.update(exam2,wrapper);
    }

    @Override
    public IPage<Exam> getByPage(Long teaNumb, Integer currentPage, Integer pageSize, Exam exam) {
        Page<Exam> page = new Page<>(currentPage,pageSize);
        LambdaQueryWrapper<Exam> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Strings.isNotEmpty(exam.getExamName()),Exam::getExamName,exam.getExamName());
        wrapper.eq(exam.getExamLevel()!=null,Exam::getExamLevel,exam.getExamLevel());
        wrapper.eq(true,Exam::getTeaNumb,exam.getTeaNumb());
        return examMapper.selectPage(page, wrapper);
    }
}
