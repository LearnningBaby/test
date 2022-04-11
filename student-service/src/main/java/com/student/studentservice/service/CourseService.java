package com.student.studentservice.service;

import com.student.studentservice.domain.Course;

import java.util.List;

public interface CourseService  {
    List<Course> getCourseInfo(Long stu_numb);
}
