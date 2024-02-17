package com.usg.BookAndBeanstalkMember.adaptor.in.web;

import com.usg.BookAndBeanstalkMember.adaptor.out.persistance.MemberEmailCheckEntity;
import com.usg.BookAndBeanstalkMember.adaptor.out.persistance.MemberJoinEntity;
import com.usg.BookAndBeanstalkMember.adaptor.out.persistance.MemberLoginEntity;
import com.usg.BookAndBeanstalkMember.adaptor.out.persistance.MemberNicknameCheckEntity;
import com.usg.BookAndBeanstalkMember.application.usecases.CheckMemberUseCase;
import com.usg.BookAndBeanstalkMember.application.usecases.JoinMemberUseCase;
import com.usg.BookAndBeanstalkMember.application.usecases.LoginMemberUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {

    private final JoinMemberUseCase joinMemberUseCase;
    private final LoginMemberUseCase loginMemberUseCase;
    private final CheckMemberUseCase checkMemberUseCase;

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody MemberJoinEntity dto) {
        try {
            joinMemberUseCase.join(dto.getEmail(), dto.getPassword(), dto.getNickname());
            return ResponseEntity.ok("join success");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MemberLoginEntity loginDto) {
        System.out.println("== controller 진입");
        System.out.println(loginDto.getEmail() + " " + loginDto.getPassword());
        try {
            String token = loginMemberUseCase.login(loginDto);
            System.out.println("생성된 token " + token);
            // JSON 형식으로 응답 데이터 생성
            String jsonResponse = "{\"token\": \"" + token + "\"}";

            return ResponseEntity.ok(jsonResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/email")
    public ResponseEntity<String> checkEmailDuplicate(@RequestBody MemberEmailCheckEntity memberEmailCheckEntity) {
        try {
            boolean isAvailable =checkMemberUseCase.checkEmailDuplicate(memberEmailCheckEntity.getEmail());

            // JSON 형식으로 응답 데이터 생성
            String jsonResponse = "{\"isAvailable\": " + isAvailable + "}";
            return ResponseEntity.ok(jsonResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/nickname")
    public ResponseEntity<String> checkNicknameDuplicate(@RequestBody MemberNicknameCheckEntity memberNicknameCheckEntity) {
        try {
            boolean isAvailable = checkMemberUseCase.checkNicknameDuplicate(memberNicknameCheckEntity.getNickname());

            // JSON 형식으로 응답 데이터 생성
            String jsonResponse = "{\"isAvailable\": " + isAvailable + "}";
            return ResponseEntity.ok(jsonResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}