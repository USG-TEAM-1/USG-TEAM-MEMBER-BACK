package com.usg.BookAndBeanstalkMember.application.usecases;

import com.usg.BookAndBeanstalkMember.adaptor.out.persistance.MemberLoginEntity;

public interface LoginMemberUseCase {

    String login(MemberLoginEntity loginDto);
}
