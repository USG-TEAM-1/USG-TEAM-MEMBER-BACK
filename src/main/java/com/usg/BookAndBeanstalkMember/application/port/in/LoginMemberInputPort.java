package com.usg.BookAndBeanstalkMember.application.port.in;

import com.usg.BookAndBeanstalkMember.adaptor.in.security.JwtUtilities;
import com.usg.BookAndBeanstalkMember.adaptor.out.persistance.MemberLoginEntity;
import com.usg.BookAndBeanstalkMember.application.port.out.MemberFindOutputPort;
import com.usg.BookAndBeanstalkMember.application.usecases.LoginMemberUseCase;
import com.usg.BookAndBeanstalkMember.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LoginMemberInputPort implements LoginMemberUseCase {

    private final JwtUtilities jwtUtilities;
    private final MemberFindOutputPort memberFindOutputPort;

    @Override
    public String login(MemberLoginEntity loginDto) {
        System.out.println("== login input port 진입");
        System.out.println(loginDto.getEmail() + " " + loginDto.getPassword());

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword());

        System.out.println("login authentication 생성");

        SecurityContextHolder.getContext().setAuthentication(authentication);
        Member member = memberFindOutputPort.findOne(authentication.getName()).orElseThrow(() ->
                new UsernameNotFoundException("User not found"));
        System.out.println("get user data");
        System.out.println(member.getEmail() + " " + member.getPassword());
        return jwtUtilities.generateToken(member.getEmail());
    }
}
