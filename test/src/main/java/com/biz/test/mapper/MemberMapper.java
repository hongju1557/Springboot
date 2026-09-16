package com.biz.test.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.biz.test.domain.Member;

@Mapper
public interface MemberMapper {

    Member findById(String memberId);

}
