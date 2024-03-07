package com.sparta.back_office.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.back_office.model.dto.request.SignInRequestDto;
import com.sparta.back_office.model.enums.Auth;
import com.sparta.back_office.security.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

import static com.sparta.back_office.constants.ManagerConstants.*;

@Slf4j(topic = "로그인 및 JWT 생성")
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtUtil jwtUtil;
    private String filterProcessUrl = "/api/manager/login";

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
        setFilterProcessesUrl(filterProcessUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("로그인 시도");
        try {
            SignInRequestDto requestDto = new ObjectMapper().readValue(request.getInputStream(), SignInRequestDto.class);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.getEmail(),
                            requestDto.getPw(),
                            null
                    )
            );
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("로그인 성공 및 JWT 생성");
        String token = makeTokenByAuthResult(authResult);
        jwtUtil.addJwtToCookie(token, response);

        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(String.format(LOGIN_COMPLETE, getUsernameByAuthResult(authResult)));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.info("로그인 실패");
        response.setStatus(401);
        response.setCharacterEncoding("UTF-8");
        if (failed instanceof UsernameNotFoundException) {
            // 비밀번호 오류일 경우
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(NOT_MATCH_INFORMATION);
        } else if (failed instanceof UsernameNotFoundException) {
            // 아이디 오류일 경우
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(NOT_MATCH_INFORMATION);
        } else {
            // 기타 인증 오류 처리
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(ANOTHER_PROBLEM);
        }
    }


    private String makeTokenByAuthResult(Authentication authResult) {
        String username = getUsernameByAuthResult(authResult);
        Auth role = getAuthByAuthResult(authResult);
        return jwtUtil.createToken(username, role);
    }

    private Auth getAuthByAuthResult(Authentication authResult) {
        return ((UserDetailsImpl) authResult.getPrincipal()).getUser().getAuth();
    }

    private String getUsernameByAuthResult(Authentication authResult) {
        String username = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();
        return username;
    }
}