package com.usg.BookAndBeanstalkMember.application.port.in;

import com.usg.BookAndBeanstalkMember.application.port.out.MemberJoinOutputPort;
import com.usg.BookAndBeanstalkMember.application.usecases.JoinMemberUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegisterMemberInputPort implements JoinMemberUseCase {

    private final MemberJoinOutputPort memberJoinOutputPort;

    @Override
    public void join(String email, String password, String nickname) {

        memberJoinOutputPort.join(email, password, nickname);
    }
}
