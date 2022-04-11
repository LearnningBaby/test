package com.student.studentservice.dao;

import com.student.studentservice.mapper.CourseMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional
@SpringBootTest
public class CourseTest {
    @Resource
    private CourseMapper courseMapper;
    @Test
    public void test01(){
        System.out.println(courseMapper.getCourseInfo(1L));
    }
}
