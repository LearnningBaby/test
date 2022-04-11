package com.teacher.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.teacher.domain.Exam;
import com.teacher.domain.ExamAll;
import com.teacher.domain.ExamMy;

public interface ExamService extends IService<Exam> {
    void autoExam(ExamAll examAll);

    void manualExam(ExamMy examMy);

    IPage<Exam> getByPage(Long teaNumb, Integer currentPage, Integer pageSize, Exam exam);
}
