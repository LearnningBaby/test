package com.admin.domain;

import lombok.Data;

@Data
public class ShortAnswer extends Qu{
    private Long id;
    private String desc;
    private String ans;
    private String explain;
}
