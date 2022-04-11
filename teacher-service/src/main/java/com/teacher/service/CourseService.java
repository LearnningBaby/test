package com.teacher.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.teacher.domain.Course;
import com.teacher.domain.CourseAll;

import java.util.List;

public interface CourseService extends IService<Course> {
    List<CourseAll> getCourse(Long teaNumb);
}
