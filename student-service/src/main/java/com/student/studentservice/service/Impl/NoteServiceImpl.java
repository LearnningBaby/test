package com.student.studentservice.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.student.studentservice.domain.Note;
import com.student.studentservice.mapper.MessageMapper;
import com.student.studentservice.mapper.NoteMapper;
import com.student.studentservice.service.NoteService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class NoteServiceImpl extends ServiceImpl<NoteMapper, Note> implements NoteService {

    @Resource
    private NoteMapper noteMapper;

    @Resource
    private MessageMapper messageMapper;

    /**
     * 查询笔记信息
     * @param stu_numb
     * @param currentPage
     * @param pageSize
     * @param note
     * @return
     */
    @Override
    public IPage<Note> getByPage(Integer stu_numb, Integer currentPage, Integer pageSize, Note note) {
        Long mesId = messageMapper.getMesId(stu_numb);
        Page<Note> page = new Page<>(currentPage,pageSize);
        LambdaQueryWrapper<Note> lqw = new LambdaQueryWrapper<>();
        lqw.like(Strings.isNotEmpty(note.getTitle()),Note::getTitle,note.getTitle());
        lqw.eq(true,Note::getHandMsgNumb,mesId);
        return noteMapper.selectPage(page,lqw);
    }

    @Override
    public boolean delById(Integer note_numb) {
        return noteMapper.delById(note_numb);
    }

    @Override
    public boolean addNote(Note note) {
        Long mesId = messageMapper.getMesId(note.getHandMsgNumb());
        note.setHandMsgNumb(Math.toIntExact(mesId));
        return noteMapper.insert(note)>0;
    }
}
