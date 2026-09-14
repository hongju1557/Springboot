package com.biz.test.dto;

public enum EmpSort {

    EMP_ID("e.EMP_ID", "ASC"),
    HIRE_DATE("e.HIRE_DATE", "DESC"),
    SALARY("e.SALARY", "DESC"),
    NAME("e.EMP_NAME", "ASC");

    private final String column;
    private final String direction;

    EmpSort(String column, String direction) {
        this.column = column;
        this.direction = direction;
    }

    public String getColumn() {
        return column;
    }

    public String getDirection() {
        return direction;
    }
}