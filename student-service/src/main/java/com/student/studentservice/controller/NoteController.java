package com.student.studentservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.student.studentservice.domain.Note;
import com.student.studentservice.domain.R;
import com.student.studentservice.service.Impl.NoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/student")
public class NoteController {

    @Autowired
    private NoteServiceImpl noteService;

    @GetMapping("/note/{stu_numb}/{currentPage}/{pageSize}")
    public R getNote(@PathVariable Integer stu_numb,@PathVariable Integer currentPage,@PathVariable Integer pageSize, Note note){
        IPage<Note> page = noteService.getByPage(stu_numb, currentPage, pageSize, note);
        if (currentPage>page.getPages()){
            page=noteService.getByPage(stu_numb,currentPage,pageSize,note);
        }
        return new R(true,"查询成功!",page);
    }

    @PostMapping("/note")
    public R addNote(@RequestBody Note note){
        boolean save = noteService.addNote(note);
        if (save){
            return new R(true,"提交成功!",null);
        }else {
            return new R(false,"提交失败!",null);
        }
    }

    @DeleteMapping("/note/{note_numb}")
    public R delNote(@PathVariable Integer note_numb){
        boolean save = noteService.delById(note_numb);
        if (save){
            return new R(true,"删除成功!",null);
        }else {
            return new R(false,"删除失败!",null);
        }
    }

    @GetMapping("/note/{note_numb}")
    public R getNoteOne(@PathVariable Integer note_numb){
        QueryWrapper<Note> wrapper = new QueryWrapper<>();
        wrapper.eq("note_numb",note_numb);
        Note note = noteService.getOne(wrapper);
        return new R(true,"查询成功!",note);
    }

    @PutMapping("/note")
    public R updateNote(@RequestBody Note note){
        QueryWrapper<Note> wrapper = new QueryWrapper<>();
        wrapper.eq("note_numb",note.getNoteNumb());
        note.setNoteNumb(null);
        boolean save = noteService.update(note,wrapper);
        if (save){
            return new R(true,"提交成功!",null);
        }else {
            return new R(false,"提交失败!",null);
        }
    }

    @PostMapping("/delAllNote")
    public R delAllNote(@RequestBody Integer[] noteNumb){
        for (Integer integer : noteNumb) {
            boolean b = noteService.delById(integer);
            if (!b){
                return new R(false,"删除失败!",null);
            }
        }
        return new R(true,"删除成功!",null);
    }
}
