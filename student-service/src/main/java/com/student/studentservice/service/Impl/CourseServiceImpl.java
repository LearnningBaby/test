package com.student.studentservice.service.Impl;

import com.student.studentservice.domain.Course;
import com.student.studentservice.mapper.CourseMapper;
import com.student.studentservice.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Resource
    private CourseMapper courseMapper;

    /**
     * 查询课程信息
     * @param stu_numb
     * @return
     */
    @Override
    public List<Course> getCourseInfo(Long stu_numb) {
        List<Long> courseNumb = courseMapper.getCourseNumb(stu_numb);
        if (courseNumb==null){
            return null;
        }else {
            List<Course> courseList = new ArrayList<>();
            for (Long aLong : courseNumb) {
                Course course = courseMapper.getCourseInfo(aLong);
                courseList.add(course);
            }
            return courseList;
        }
    }
}
