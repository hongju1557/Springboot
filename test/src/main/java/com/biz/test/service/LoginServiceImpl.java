
package com.biz.test.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.biz.test.domain.Member;
import com.biz.test.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final MemberMapper memberMapper;
    private final BCryptPasswordEncoder encoder;

    // 실패 횟수 업데이트 후 예외를 던지므로,
    // 여기에 기본 설정의 @Transactional을 붙이면 업데이트가 롤백될 수 있습니다.
    @Override
    public Member login(String memberId, String password) {

        Member member = memberMapper.findById(memberId);

        if (member == null) {
            throw new IllegalArgumentException(
                    "아이디 또는 비밀번호가 올바르지 않습니다."
            );
        }

        if (member.getIsLocked() == 1) {
            throw new IllegalArgumentException(
                    "비밀번호 5회 실패로 잠긴 계정입니다. 관리자에게 문의하세요."
            );
        }

        if (!encoder.matches(password, member.getPassword())) {

            memberMapper.updateFailCount(memberId);

            Member updated = memberMapper.findById(memberId);

            if (updated != null && updated.getIsLocked() == 1) {
                throw new IllegalArgumentException(
                        "비밀번호 5회 실패로 계정이 잠겼습니다. 관리자에게 문의하세요."
                );
            }

            throw new IllegalArgumentException(
                    "아이디 또는 비밀번호가 올바르지 않습니다."
            );
        }

        int updated = memberMapper.resetFailCount(memberId);

        if (updated == 0) {
            throw new IllegalArgumentException(
                    "로그인할 수 없는 계정입니다. 관리자에게 문의하세요."
            );
        }

        return memberMapper.findById(memberId);
    }
}