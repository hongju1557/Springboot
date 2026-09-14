package com.biz.test.dto;

import lombok.Data;

@Data
public class EmpSearchCond {

    private String keyword;
    private String deptId;

    private boolean activeOnly;

    private EmpSort sort = EmpSort.EMP_ID;

    private int page = 1;

    private int size = 10;

    public int getOffset() {
        int p = Math.max(page, 1);

        return (p - 1) * size;
    }
}