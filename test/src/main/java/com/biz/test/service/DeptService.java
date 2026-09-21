package com.biz.test.service;

import java.util.List;

import com.biz.test.dto.Dept;

public interface DeptService {

    List<Dept> findAll();

    void save(Dept dept);

    void delete(String deptId);
}
