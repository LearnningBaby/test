package com.student.studentservice.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.student.studentservice.domain.Student;
import com.student.studentservice.service.Impl.FaceServiceImpl;
import com.student.studentservice.service.Impl.StuServiceImpl;
import com.student.studentservice.service.Impl.TestServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class FaceTest {

    @Autowired
    private FaceServiceImpl faceService;

    @Autowired
    private StuServiceImpl stuService;

    @Autowired
    private TestServiceImpl testService;

    @Test
    void face() throws IOException {
//        byte[] bytes1 = FileUtil.readFileByBytes("C:\\Users\\86178\\Desktop\\计算机程序设计\\images\\999.jpg");
//        String encode1 = Base64Util.encode(bytes1);
//
//        byte[] bytes2 =FileUtil.readFileByBytes("C:\\Users\\86178\\Desktop\\计算机程序设计\\images\\888.jpg");
//        String encode2 = Base64Util.encode(bytes2);
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.eq("stu_numb",1);
        Student one = stuService.getOne(wrapper);
        String stuHead = testService.getStuHead(one);
    }
}
