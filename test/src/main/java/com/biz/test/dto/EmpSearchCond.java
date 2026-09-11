package com.biz.test.dto;

public class EmpSearchCond {

    private String keyword;
    private String deptId;

    private boolean workingOnly = false;

    private String sort = "hireDate";

    private int page = 1;

    private int pageSize = 10;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public boolean isWorkingOnly() {
        return workingOnly;
    }

    public void setWorkingOnly(boolean workingOnly) {
        this.workingOnly = workingOnly;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getOffset() {
        int p = Math.max(page, 1);

        return (p - 1) * pageSize;
    }
}