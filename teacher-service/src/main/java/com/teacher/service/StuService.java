package com.teacher.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.teacher.domain.Class;
import com.teacher.domain.Ins;
import com.teacher.domain.Student;
import com.teacher.domain.StudentDetail;

import java.util.List;

public interface StuService extends IService<StudentDetail> {
    List<Class> getClassInfo(Long teaNumb);

    IPage<StudentDetail> getStuInfo(Long clsNumb, Long currentPage, Long pageSize, StudentDetail student);

    List<Ins> getUnitInfo();

    boolean addStu(Student student);

    boolean delStu(Long ins_numb, Long stu_id);

    boolean bulkImport(Student student);
}
