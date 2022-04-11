package com.teacher.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teacher.config.constant;
import com.teacher.domain.Class;
import com.teacher.domain.Ins;
import com.teacher.domain.Student;
import com.teacher.domain.StudentDetail;
import com.teacher.mapper.StuMapper;
import com.teacher.service.StuService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class StuServiceImpl extends ServiceImpl<StuMapper, StudentDetail> implements StuService {

    @Resource
    private StuMapper stuMapper;

    @Override
    public List<Class> getClassInfo(Long teaNumb) {
        List<Long> clsNumb = stuMapper.selectClsNumb(teaNumb);
        if (!clsNumb.isEmpty()){
            ArrayList<Class> classes = new ArrayList<>();
            for (Long aLong : clsNumb) {
                Class aClass = stuMapper.selectClass(aLong);
                classes.add(aClass);
            }
            return classes;
        }else {
            return null;
        }
    }

    @Override
    public IPage<StudentDetail> getStuInfo(Long clsNumb, Long currentPage, Long pageSize, StudentDetail student) {
        String className = stuMapper.getClassName(clsNumb);
        Page<StudentDetail> page = new Page<>(currentPage,pageSize);
        LambdaQueryWrapper<StudentDetail> lqw = new LambdaQueryWrapper<>();
        lqw.like(Strings.isNotEmpty(student.getStuName()),StudentDetail::getStuName,student.getStuName());
        lqw.eq(true,StudentDetail::getClsName,className);
        return stuMapper.selectPage(page,lqw);
    }

    @Override
    public List<Ins> getUnitInfo() {
        return stuMapper.getIns();
    }

    @Override
    public boolean addStu(Student student) {
        StudentDetail studentDetail = new StudentDetail();
        studentDetail.setInsNumb(student.getInsNumb());
        studentDetail.setStuId(student.getStuId());
        studentDetail.setClsName(stuMapper.getClassName(student.getClsNumb()));
        studentDetail.setGender(constant.GENDER);
        studentDetail.setStuName(constant.NAME);
        student.setNickPhoto(constant.NICK_PHOTO);
        student.setNickName(constant.NICK_NAME);
        student.setOnlineStatus(constant.ONLINE_STATUS);
        return stuMapper.insert(studentDetail)>0 && stuMapper.addStu(student);
    }

    @Override
    public boolean delStu(Long ins_numb, Long stu_id) {
        QueryWrapper<StudentDetail> wrapper = new QueryWrapper<>();
        wrapper.eq("ins_numb",ins_numb);
        wrapper.eq("stu_id",stu_id);
        return stuMapper.delStu(ins_numb,stu_id) && stuMapper.delete(wrapper)>0;
    }

    @Override
    public boolean bulkImport(Student student) {
        int number=student.getNumber();
        String stu_id=student.getStuId().toString();
        for (int i = 1; i <= number; i++) {
            StringBuilder builder = new StringBuilder();
            builder.append(stu_id);
            student.setStuId((long) Integer.parseInt(builder.append(i).toString()));
            boolean b = addStu(student);
            if (!b){
                return false;
            }
        }
        return true;
    }
}
