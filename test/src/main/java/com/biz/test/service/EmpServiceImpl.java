package com.biz.test.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.test.dto.Emp;
import com.biz.test.dto.EmpSearchCond;
import com.biz.test.dto.PageResult;
import com.biz.test.mapper.EmpMapper;

@Service
public class EmpServiceImpl implements EmpService {

    private final EmpMapper empMapper;

    public EmpServiceImpl(EmpMapper empMapper) {
        this.empMapper = empMapper;
    }

    @Override
    public PageResult<Emp> findPage(EmpSearchCond cond) {

        // 조건에 맞는 사원 목록 조회
        List<Emp> content = empMapper.findPage(cond);

        // 조건에 맞는 전체 사원 수 조회
        long totalElements = empMapper.countPage(cond);

        // 목록 + 전체 개수 + 현재 페이지 + 페이지 크기
        return new PageResult<>(
                content,
                totalElements,
                cond.getPage(),
                cond.getSize()
        );
    }

    @Override
    public Emp findById(String empId) {
        return empMapper.findById(empId);
    }

    @Override
    public void insert(Emp emp) {
        empMapper.insert(emp);
    }

    @Override
    public void update(Emp emp) {
        empMapper.update(emp);
    }
}