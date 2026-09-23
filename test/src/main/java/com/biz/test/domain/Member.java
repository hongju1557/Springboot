// 2. src/main/java/com/biz/test/domain/Member.java
// 기존 파일 교체

package com.biz.test.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    private String memberId;
    private String password;
    private String memberName;
    private String role;

    private int loginFailCount;
    private int isLocked;
    private LocalDateTime lastLoginAt;
}