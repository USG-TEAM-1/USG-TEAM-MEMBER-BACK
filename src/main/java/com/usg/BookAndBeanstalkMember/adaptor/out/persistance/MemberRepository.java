package com.usg.BookAndBeanstalkMember.adaptor.out.persistance;

import com.usg.BookAndBeanstalkMember.application.port.out.MemberFindOutputPort;
import com.usg.BookAndBeanstalkMember.application.port.out.MemberJoinOutputPort;
import com.usg.BookAndBeanstalkMember.domain.Member;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MemberRepository implements MemberFindOutputPort, MemberJoinOutputPort {

    private final PasswordEncoder passwordEncoder;
    private final JpaMemberRepository repository;

    public MemberRepository(PasswordEncoder passwordEncoder, JpaMemberRepository repository) {
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
    }

    @Override
    public Optional<Member> findOne(String userId) {

        return repository.findByUserid(userId);
    }

    @Override
    public Long join(String userid, String pw) {
        Member member = Member.createUser(userid, pw, passwordEncoder);
        validateDuplicateMember(member);
        repository.save(member);

        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        repository.findByUserid(member.getEmail())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
}
