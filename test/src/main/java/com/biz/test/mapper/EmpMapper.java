package com.biz.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.biz.test.dto.Emp;
import com.biz.test.dto.EmpSearchCond;


@Mapper
public interface EmpMapper{

    List<Emp> selectByCond(EmpSearchCond cond);

    int insert(Emp emp);

    Emp findById(String empId);
}