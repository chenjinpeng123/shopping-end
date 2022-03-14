package com.example.demo.util;

import lombok.Data;

import java.util.List;

@Data
public class BackPage<T> {

    private static final long serialVersionUID=1L;

    /**
     * 总页数
     */
    private long totalPage;

    /**
     * 当前页数
     */
    private long currentPage;

    /**
     * 总数
     */
    private long totalNum;

    /**
     * 内容
     */
    private List<T> contentList;
}
