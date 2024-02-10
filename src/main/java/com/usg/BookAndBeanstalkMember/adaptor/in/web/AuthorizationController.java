package com.usg.BookAndBeanstalkMember.adaptor.in.web;

import com.usg.BookAndBeanstalkMember.adaptor.out.persistance.MemberJoinEntity;
import com.usg.BookAndBeanstalkMember.application.usecases.JoinMemberUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {

    private final JoinMemberUseCase joinMemberUseCase;

    public AuthorizationController(JoinMemberUseCase joinMemberUseCase) {
        this.joinMemberUseCase = joinMemberUseCase;
    }

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody MemberJoinEntity dto) {
        try {
            joinMemberUseCase.join(dto.getEmail(), dto.getPassword());
            return ResponseEntity.ok("join success");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
