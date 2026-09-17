package com.biz.test.service;

import com.biz.test.dto.Emp;
import com.biz.test.dto.EmpSearchCond;
import com.biz.test.dto.PageResult;

public interface EmpService {

    PageResult<Emp> findPage(EmpSearchCond cond);

    Emp findById(String empId);

    void insert(Emp emp);
}
