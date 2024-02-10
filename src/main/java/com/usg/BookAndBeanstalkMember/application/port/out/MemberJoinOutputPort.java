package com.usg.BookAndBeanstalkMember.application.port.out;

public interface MemberJoinOutputPort {

    Long join(String userid, String pw);
}
