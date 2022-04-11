package com.student.studentservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.student.studentservice.domain.Note;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoteMapper extends BaseMapper<Note> {

    @Delete("delete from note_table where note_numb=#{note_numb}")
    boolean delById(Integer note_numb);
}
