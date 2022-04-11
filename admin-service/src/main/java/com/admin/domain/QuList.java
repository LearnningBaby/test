package com.admin.domain;

import lombok.Data;

import java.util.List;

@Data
public class QuList<T> {
    private List<T> list;
    private Long number;
}
