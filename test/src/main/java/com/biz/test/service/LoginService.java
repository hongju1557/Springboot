
package com.biz.test.service;

import com.biz.test.domain.Member;

public interface LoginService {

    Member login(String memberId, String password);
}