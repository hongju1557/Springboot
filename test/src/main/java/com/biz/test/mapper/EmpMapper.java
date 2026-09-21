package com.biz.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.biz.test.dto.Emp;
import com.biz.test.dto.EmpSearchCond;

@Mapper
public interface EmpMapper {

    // 검색 + 페이징
    List<Emp> findPage(EmpSearchCond cond);

    // 사원 추가
    int insert(Emp emp);

    // 사원 1명 조회
    Emp findById(String empId);

    // 검색 결과 총 개수
    long countPage(EmpSearchCond cond);

    // 여러 사원번호로 한꺼번에 조회
    List<Emp> findByIds(@Param("ids") List<String> ids);

    int update(Emp emp);
}