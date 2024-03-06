package com.sparta.back_office.model.entity;

import com.sparta.back_office.model.dto.request.SignUpRequestDto;
import com.sparta.back_office.model.enums.Auth;
import com.sparta.back_office.model.enums.Team;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="managers")
@Getter
@NoArgsConstructor
public class Manager {
    @Id
    private String email;

    @Column(name="pw", nullable = false)
    private String pw;

    @Column(name="team", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Team team;

    @Column(name="auth", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Auth auth;

    public Manager(SignUpRequestDto signUpRequestDto) {
        this.email = signUpRequestDto.getEmail();
        this.pw = signUpRequestDto.getPw();
        this.team = signUpRequestDto.getTeam();
        this.auth = signUpRequestDto.getAuth();

    }
}
