package com.biz.test.dto;

import java.util.List;

public class PageResult<T> {

    private static final int PAGE_WINDOW = 5;

    private final List<T> content;
    private final int totalElements;
    private final int page;
    private final int pageSize;

    public PageResult(List<T> content, int totalElements, int page, int pageSize) {
        this.content = content;
        this.totalElements = totalElements;
        this.page = page;
        this.pageSize = pageSize;
    }

    public List<T> getContent() {
        return content;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalPages() {
        return Math.max(1,
                (int) Math.ceil((double) totalElements / pageSize));
    }

    public boolean isHasPrev() {
        return page > 1;
    }

    public boolean isHasNext() {
        return page < getTotalPages();
    }

    public int getStartPage() {
        return ((page - 1) / PAGE_WINDOW) * PAGE_WINDOW + 1;
    }

    public int getEndPage() {
        return Math.min(
                getStartPage() + PAGE_WINDOW - 1,
                getTotalPages()
        );
    }
}