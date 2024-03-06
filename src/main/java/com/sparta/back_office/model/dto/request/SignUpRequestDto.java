package com.sparta.back_office.model.dto.request;

import com.sparta.back_office.model.enums.Auth;
import com.sparta.back_office.model.enums.Team;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SignUpRequestDto {
    @Email
    private String email;
    @Size(min = 8, max = 15)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$",
            message = "비밀번호는 최소 8자 이상, 15자 이하이며 알파벳 대소문자, 숫자, 특수문자로 구성되어야 합니다.")
    private String pw;
    private Team team;
    private Auth auth;
}
