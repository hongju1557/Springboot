// 4. src/main/java/com/biz/test/mapper/MemberMapper.java
// 기존 파일 교체

package com.biz.test.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.biz.test.domain.Member;

@Mapper
public interface MemberMapper {

    Member findById(@Param("memberId") String memberId);

    int updateFailCount(@Param("memberId") String memberId);

    int resetFailCount(@Param("memberId") String memberId);
}