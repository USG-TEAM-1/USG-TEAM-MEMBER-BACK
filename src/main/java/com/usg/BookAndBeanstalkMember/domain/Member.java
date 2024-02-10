package com.usg.BookAndBeanstalkMember.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    private String roles;

    public static Member createUser(String email, String pw, PasswordEncoder passwordEncoder) {
        return new Member(null, email, passwordEncoder.encode(pw), "USER");
    }
}
