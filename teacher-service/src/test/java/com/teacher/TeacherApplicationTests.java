package com.teacher;

import com.alibaba.fastjson.JSON;
import com.teacher.domain.Blank;
import com.teacher.domain.Select;
import com.teacher.domain.ShortAnswer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class TeacherApplicationTests {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    void name() {
        Select select = new Select();
        select.setId(1L);
        select.setDesc("事务隔离级别是由谁实现的?");
        select.setAns("C");
        select.setExplain("事务隔离级别是由数据库系统实现,是数据库系统本身的一个功能.");
        List<String> selects = new ArrayList<>();
        selects.add("A.Java应用程序");
        selects.add("B.Hibernate");
        selects.add("C.数据库系统");
        selects.add("D.JDBC驱动程序");
        select.setChoice(selects);
        redisTemplate.opsForHash().put("bankNumb:1:questType:1","questNumb:3", JSON.toJSONString(select));
//        Set<Object> keys = redisTemplate.opsForHash().keys("bankNumb:1:questType:1");
//        for (Object key : keys) {
//            Object o = redisTemplate.opsForHash().get("bankNumb:1:questType:1", key.toString());
//            System.out.println(JSON.parseObject(o.toString(),Select.class));
//        }
    }

    @Test
    void add() {
        Blank blank = new Blank();
        blank.setId(1L);
        blank.setDesc("Java面向对象的三大特性是(),(),()");
        ArrayList<String> strings = new ArrayList<>();
        strings.add("封装");
        strings.add("多态");
        strings.add("重载");
//        blank.setAnswer(strings);
        blank.setDffLev(1);
        blank.setFreLev(0L);
//        blank.setQuType(String.valueOf(2));
        blank.setExplain("暂无解释");
        redisTemplate.opsForHash().put("bankNumb:1:questType:2","questNumb:1", JSON.toJSONString(blank));
    }

    @Test
    void AS() {
        ShortAnswer shortAnswer = new ShortAnswer();
        shortAnswer.setId(1L);
        shortAnswer.setDesc("简单叙述Java语言的三大特性.");
        shortAnswer.setExplain("封装,继承,多态");
//        shortAnswer.setAnswer("封装,继承,多态");
        shortAnswer.setDffLev(1);
        shortAnswer.setFreLev(Long.valueOf(0));
//        shortAnswer.setQuType(String.valueOf(3));
        shortAnswer.setScore(10);
        redisTemplate.opsForHash().put("bankNumb:1:questType:3","questNumb:1", JSON.toJSONString(shortAnswer));
    }
}
