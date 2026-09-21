package com.biz.test.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.test.dto.Dept;
import com.biz.test.mapper.DeptMapper;

@Service
public class DeptServiceImpl implements DeptService {

    private final DeptMapper deptMapper;

    public DeptServiceImpl(DeptMapper deptMapper) {
        this.deptMapper = deptMapper;
    }

    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Override
    public void save(Dept dept) {
        deptMapper.save(dept);
    }

    @Override
    public void delete(String deptId) {

    if (deptMapper.countEmployees(deptId) > 0) {
        throw new IllegalStateException(
                "소속 사원이 있는 부서는 삭제할 수 없습니다."
        );
    }

    int deleted = deptMapper.delete(deptId);

    if (deleted == 0) {
        throw new IllegalArgumentException(
                "존재하지 않거나 이미 삭제된 부서입니다."
        );
    }
    }
}
