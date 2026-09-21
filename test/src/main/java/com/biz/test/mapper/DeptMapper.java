package com.biz.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.biz.test.dto.Dept;

@Mapper
public interface DeptMapper {

    List<Dept> findAll();

    int save(Dept dept);

    int countEmployees(String deptId);

    int delete(String deptId);
}