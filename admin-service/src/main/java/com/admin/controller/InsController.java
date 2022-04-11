package com.admin.controller;

import com.admin.domain.Ins;
import com.admin.domain.R;
import com.admin.filter.BaseResponse;
import com.admin.service.impl.InsServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@BaseResponse
@RestController
//@RequestMapping("/admin")
public class InsController {

    @Autowired
    private InsServiceImpl insService;

    @GetMapping("/institution/{currentPage}/{pageSize}")
    public IPage<Ins> getIns(@PathVariable Integer currentPage,@PathVariable Integer pageSize,Ins ins){
        IPage<Ins> page = insService.getByPage(currentPage, pageSize, ins);
        if (currentPage>page.getPages()){
            page = insService.getByPage(currentPage, pageSize, ins);
        }
        return page;
    }

    @PutMapping("/institution")
    public R updateInstitution(@RequestBody Ins ins){
        QueryWrapper<Ins> wrapper = new QueryWrapper<>();
        wrapper.eq("ins_numb",ins.getInsNumb());
        ins.setInsNumb(null);
        insService.update(ins,wrapper);
        return new R(true,"修改成功!",null);
    }

    @DeleteMapping("/institution/{insNumb}")
    public R delInstitution(@PathVariable Long insNumb){
        QueryWrapper<Ins> wrapper = new QueryWrapper<>();
        wrapper.eq("ins_numb",insNumb);
        insService.remove(wrapper);
        return new R(true,"删除成功!",null);
    }

    @PostMapping("/delAllInstitution")
    public R delAllInstitution(@RequestBody List<Long> list){
        for (Long aLong : list) {
            QueryWrapper<Ins> wrapper = new QueryWrapper<>();
            wrapper.eq("ins_numb",aLong);
            insService.remove(wrapper);
        }
        return new R(true,"删除成功!",null);
    }

    @PostMapping("/institution")
    public R addInstitution(@RequestBody Ins ins){
       insService.save(ins);
       return new R(true,"添加成功!",null);
    }
}
