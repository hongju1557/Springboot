package com.biz.test.dto;

import lombok.Data;

@Data
public class Emp {

    private String empId;
    private String empName;
    private String email;
    private String deptId;
    private String jobCode;
    private Integer salary;
    private String hireDate;
}