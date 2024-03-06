package com.sparta.back_office.service;

import com.sparta.back_office.exception.manager.SignUpDuplicationException;
import com.sparta.back_office.model.dto.request.SignUpRequestDto;
import com.sparta.back_office.model.dto.response.SignUpResponseDto;
import com.sparta.back_office.model.entity.Manager;
import com.sparta.back_office.model.enums.Team;
import com.sparta.back_office.model.enums.Auth;
import com.sparta.back_office.exception.manager.SignUpInputException;
import com.sparta.back_office.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerRepository managerRepository;
    private final PasswordEncoder passwordEncoder;


    // 회원가입
    public String signUp(SignUpRequestDto requestDto) {
        // 권한 검증
        if(requestDto.getTeam()== Team.MARKETING&&requestDto.getAuth()== Auth.MANAGER)
            throw new SignUpInputException("마케팅 팀은 MANGER 권한을 가질 수 없습니다");

        // e-mail 중복확인

        String email = requestDto.getEmail();
        Optional<Manager> checkEmail = managerRepository.findById(email);
        if(checkEmail.isPresent())
            throw new SignUpDuplicationException("이미 가입된 이메일입니다");

        String pw = passwordEncoder.encode(requestDto.getPw());
        requestDto.setPw(pw);
        String saveEmail = managerRepository.save(new Manager(requestDto)).getEmail();
        String message = "<"+saveEmail+"> 가입 완료되었습니다";
        return message;


    }
}
