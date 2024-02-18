package com.usg.BookAndBeanstalkMember.application.port.in;

import com.usg.BookAndBeanstalkMember.adaptor.out.kafka.MemberPublishDTO;
import com.usg.BookAndBeanstalkMember.application.port.out.MemberJoinOutputPort;
import com.usg.BookAndBeanstalkMember.application.port.out.MemberKafkaOutPort;
import com.usg.BookAndBeanstalkMember.application.usecases.JoinMemberUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegisterMemberInputPort implements JoinMemberUseCase {

    private final MemberJoinOutputPort memberJoinOutputPort;
    private final MemberKafkaOutPort memberKafkaOutPort;

    @Override
    public void join(String userid, String pw) {

        Long savedMemberId = memberJoinOutputPort.join(userid, pw);

        // nickname 추가 예정
        MemberPublishDTO memberPublishDTO = createMemberPublishDTO(savedMemberId, userid, null);
        memberKafkaOutPort.publishMember(memberPublishDTO);
    }

    private MemberPublishDTO createMemberPublishDTO(Long memberId, String email, String nickname) {
        return MemberPublishDTO
                .builder()
                .memberId(memberId)
                .email(email)
//                .nickname()
                .build();
    }
}
