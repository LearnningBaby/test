package com.teacher.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.teacher.domain.*;

public interface BankService extends IService<Bank> {
    IPage<Bank> getByPage(Long teaNumb, Long currentPage, Long pageSize, Bank bank);
    QuList<Select> getQuSelectList(Long bankNumb);
    QuList<Blank> getQuBlankList(Long bankNumb);
    QuList<ShortAnswer> getQuShortAnswerList(Long bankNumb);

    void addQuSelect(Select select);

    void addQuBlank(Blank blank);

    void addQuShortAnswer(ShortAnswer shortAnswer);
}
