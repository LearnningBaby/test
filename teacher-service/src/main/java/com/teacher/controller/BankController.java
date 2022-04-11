package com.teacher.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.teacher.domain.*;
import com.teacher.service.Impl.BankServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class BankController {

    @Autowired
    private BankServiceImpl bankService;

    @GetMapping("/Qu/{teaNumb}/{currentPage}/{pageSize}")
    public R getBank(@PathVariable Long teaNumb, @PathVariable Long currentPage, @PathVariable Long pageSize, Bank bank){
        IPage<Bank> page = bankService.getByPage(teaNumb, currentPage, pageSize, bank);
        if (currentPage>page.getPages()){
            page = bankService.getByPage(teaNumb, currentPage, pageSize, bank);
        }
        return new R(true,"查询成功!",page);
    }

    @PostMapping("/Qu")
    public R addBank(@RequestBody Bank bank){
        boolean b = bankService.save(bank);
        if (b){
            return new R(true,"新增成功!",null);
        }else {
            return new R(false,"新增失败!",null);
        }
    }

    @DeleteMapping("/Qu/{bankNumb}")
    public R delBank(@PathVariable Long bankNumb){
        QueryWrapper<Bank> wrapper = new QueryWrapper<>();
        wrapper.eq("bank_numb",bankNumb);
        boolean b = bankService.remove(wrapper);
        if (b){
            return new R(true,"删除成功!",null);
        }else {
            return new R(false,"删除失败!",null);
        }
    }

    @PostMapping("/delAllQu")
    public R delAllQu(@RequestBody List<Long> bankNumbList){
        for (Long aLong : bankNumbList) {
            QueryWrapper<Bank> wrapper = new QueryWrapper<>();
            wrapper.eq("bank_numb",aLong);
            boolean b = bankService.remove(wrapper);
            if (!b){
                return new R(false,"删除失败!",null);
            }
        }
        return new R(true,"批量删除成功!",null);
    }

    @GetMapping("/getQuList/{bankNumb}")
    public R getQuList(@PathVariable Long bankNumb,Select select){
        if (select.getQuType()!=null){
            if (select.getQuType()==1){
                QuList<Select> list = bankService.getQuSelectList(bankNumb);
                return new R(true,"查询成功!",list);
            }else if (select.getQuType()==2){
                QuList<Blank> list = bankService.getQuBlankList(bankNumb);
                return new R(true,"查询成功!",list);
            }else {
                QuList<ShortAnswer> list = bankService.getQuShortAnswerList(bankNumb);
                return new R(true,"查询成功!",list);
            }
        }else {
            QuList<Select> list = bankService.getQuSelectList(bankNumb);
            return new R(true,"查询成功!",list);
        }
    }

    @PostMapping("/addQu1")
    public R addQuSelect(@RequestBody Select select){
        bankService.addQuSelect(select);
        return new R(true,"添加成功!",null);
    }

    @PostMapping("/addQu2")
    public R addQuBlank(@RequestBody Blank blank){
        bankService.addQuBlank(blank);
        return new R(true,"添加成功!",null);
    }

    @PostMapping("/addQu3")
    public R addQuShortAnswer(@RequestBody ShortAnswer shortAnswer){
        bankService.addQuShortAnswer(shortAnswer);
        return new R(true,"添加成功!",null);
    }

}
