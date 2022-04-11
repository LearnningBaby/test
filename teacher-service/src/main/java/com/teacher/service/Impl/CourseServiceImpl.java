package com.teacher.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teacher.domain.Course;
import com.teacher.domain.CourseAll;
import com.teacher.mapper.CourseMapper;
import com.teacher.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Resource
    private CourseMapper courseMapper;

    @Override
    public List<CourseAll> getCourse(Long teaNumb) {
        List<Long> crsNumb = courseMapper.getCrsNumb(teaNumb);
        if (crsNumb!=null){
            ArrayList<CourseAll> courseAlls = new ArrayList<>();
            for (Long aLong : crsNumb) {
                CourseAll courseAll = new CourseAll();
                QueryWrapper<Course> wrapper = new QueryWrapper<>();
                wrapper.eq("crs_numb",aLong);
                Course course = courseMapper.selectOne(wrapper);//课程信息
                courseAll.setCrsNumb(course.getCrsNumb());
                courseAll.setBookImg(course.getBookImg());
                courseAll.setCrsName(course.getCrsName());
                courseAll.setCreateTime(course.getCreateTime());
                Integer stuNumber = courseMapper.getStuNumber(aLong);//人数
                courseAll.setCrsNumber(stuNumber);
                ArrayList<String> strings = new ArrayList<>();//班级
                List<Long> clsNumb = courseMapper.getClsNumb(aLong);
                for (Long aLong1 : clsNumb) {
                    String clsName = courseMapper.getClsName(aLong1);
                    strings.add(clsName);
                }
                courseAll.setCrsClass(strings.toArray(new String[strings.size()]));
                courseAlls.add(courseAll);
            }
            return courseAlls;
        }else {
            return null;
        }
    }
}
