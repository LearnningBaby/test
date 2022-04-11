package com.teacher.service.Impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teacher.domain.*;
import com.teacher.mapper.BankMapper;
import com.teacher.service.BankService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Service
public class BankServiceImpl extends ServiceImpl<BankMapper, Bank> implements BankService {

    @Resource
    private BankMapper bankMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public IPage<Bank> getByPage(Long teaNumb, Long currentPage, Long pageSize, Bank bank) {
        Page<Bank> page = new Page<>(currentPage,pageSize);
        LambdaQueryWrapper<Bank> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Strings.isNotEmpty(bank.getName()),Bank::getName,bank.getName());
        if (bank!=null){
            wrapper.eq(bank.getCrsNumb()!=null,Bank::getCrsNumb,bank.getCrsNumb());
        }
        IPage<Bank> selectPage = bankMapper.selectPage(page, wrapper);
        return selectPage;
    }

    /**
     * 选择题
     * @param bankNumb
     * @return
     */
    @Override
    public QuList<Select> getQuSelectList(Long bankNumb) {
        Set<Object> keys = redisTemplate.opsForHash().keys("bankNumb:" + bankNumb + ":questType:1");
        List<Select> selects = new ArrayList<>();
        for (Object key : keys) {
            Object o = redisTemplate.opsForHash().get("bankNumb:" + bankNumb + ":questType:1", key.toString());
            selects.add(JSON.parseObject(o.toString(),Select.class));
        }
        selects.sort(new Comparator<Select>() {
            @Override
            public int compare(Select o1, Select o2) {
                return Math.toIntExact(o1.getId() - o2.getId());
            }
        });
        QuList<Select> list = new QuList<>();
        list.setList(selects);
        list.setNumber((long) selects.size());
        return list;
    }

    /**
     * 填空题
     * @param bankNumb
     * @return
     */
    @Override
    public QuList<Blank> getQuBlankList(Long bankNumb) {
        Set<Object> keys = redisTemplate.opsForHash().keys("bankNumb:" + bankNumb + ":questType:2");
        List<Blank> selects = new ArrayList<>();
        for (Object key : keys) {
            Object o = redisTemplate.opsForHash().get("bankNumb:" + bankNumb + ":questType:2", key.toString());
            selects.add(JSON.parseObject(o.toString(),Blank.class));
        }
        selects.sort(new Comparator<Blank>() {
            @Override
            public int compare(Blank o1, Blank o2) {
                return Math.toIntExact(o1.getId() - o2.getId());
            }
        });
        QuList<Blank> list = new QuList<>();
        list.setList(selects);
        list.setNumber((long) selects.size());
        return list;
    }

    /**
     * 简答题
     * @param bankNumb
     * @return
     */
    @Override
    public QuList<ShortAnswer> getQuShortAnswerList(Long bankNumb) {
        Set<Object> keys = redisTemplate.opsForHash().keys("bankNumb:" + bankNumb + ":questType:3");
        List<ShortAnswer> selects = new ArrayList<>();
        for (Object key : keys) {
            Object o = redisTemplate.opsForHash().get("bankNumb:" + bankNumb + ":questType:3", key.toString());
            selects.add(JSON.parseObject(o.toString(),ShortAnswer.class));
        }
        selects.sort(new Comparator<ShortAnswer>() {
            @Override
            public int compare(ShortAnswer o1, ShortAnswer o2) {
                return Math.toIntExact(o1.getId() - o2.getId());
            }
        });
        QuList<ShortAnswer> list = new QuList<>();
        list.setList(selects);
        list.setNumber((long) selects.size());
        return list;
    }

    @Override
    public void addQuSelect(Select select) {
        Long bankNumb=select.getId();
        QuList<Select> list = getQuSelectList(bankNumb);
        select.setId((long) list.getList().size()+1);
        ArrayList<String> strings = new ArrayList<>();
        List<String> choice = select.getChoice();
        strings.add("A."+choice.get(0));
        strings.add("B."+choice.get(1));
        strings.add("C."+choice.get(2));
        strings.add("D."+choice.get(3));
        select.setChoice(strings);
        redisTemplate.opsForHash().put("bankNumb:" + bankNumb + ":questType:1","questNumb:"+select.getId(),JSON.toJSONString(select));
    }

    @Override
    public void addQuBlank(Blank blank) {
        Long bankNumb=blank.getId();
        QuList<Blank> list = getQuBlankList(bankNumb);
        blank.setId((long) (list.getList().size()+1));
        redisTemplate.opsForHash().put("bankNumb:" + bankNumb + ":questType:2","questNumb:"+blank.getId(),JSON.toJSONString(blank));
    }

    @Override
    public void addQuShortAnswer(ShortAnswer shortAnswer) {
        Long bankNumb=shortAnswer.getId();
        QuList<ShortAnswer> list = getQuShortAnswerList(bankNumb);
        shortAnswer.setId((long) (list.getList().size()+1));
        redisTemplate.opsForHash().put("bankNumb:" + bankNumb + ":questType:3","questNumb:"+shortAnswer.getId(),JSON.toJSONString(shortAnswer));
    }
}
