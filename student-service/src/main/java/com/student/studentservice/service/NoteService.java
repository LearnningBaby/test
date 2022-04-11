package com.student.studentservice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.student.studentservice.domain.Note;

public interface NoteService extends IService<Note> {
    IPage<Note> getByPage(Integer stu_numb, Integer currentPage, Integer pageSize, Note note);

    boolean delById(Integer note_numb);

    boolean addNote(Note note);
}
