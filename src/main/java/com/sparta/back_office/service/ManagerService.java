package com.sparta.back_office.service;

import com.sparta.back_office.exception.manager.SignUpDuplicationException;
import com.sparta.back_office.exception.manager.SignUpInputException;
import com.sparta.back_office.jwt.JwtUtil;
import com.sparta.back_office.model.dto.request.SignUpRequestDto;
import com.sparta.back_office.model.entity.Manager;
import com.sparta.back_office.model.enums.Auth;
import com.sparta.back_office.model.enums.Team;
import com.sparta.back_office.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.sparta.back_office.constants.ManagerConstants.*;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerRepository managerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    // 회원가입
    public String signUp(SignUpRequestDto requestDto) {
        // 권한 검증
        checkAuthValue(requestDto);
        // e-mail 중복확인
        checkDuplicateEmail(requestDto);
        // 비밀번호 암호화
        encodingPw(requestDto);

        String saveEmail = managerRepository.save(new Manager(requestDto)).getEmail();
        return String.format(SIGN_UP_COMPLETE,saveEmail);
    }

    private void encodingPw(SignUpRequestDto requestDto) {
        String pw = passwordEncoder.encode(requestDto.getPw());
        requestDto.setPw(pw);
    }

    private void checkDuplicateEmail(SignUpRequestDto requestDto) {
        String email = requestDto.getEmail();
        Optional<Manager> checkEmail = managerRepository.findById(email);
        if (checkEmail.isPresent())
            throw new SignUpDuplicationException(EMAIL_ALREADY_EXIST);
    }

    private static void checkAuthValue(SignUpRequestDto requestDto) {
        if (requestDto.getTeam() == Team.MARKETING && requestDto.getAuth() == Auth.MANAGER)
            throw new SignUpInputException(MARKETING_CANT_GET_MANAGER);
    }

}
