package com.sparta.back_office.model.dto.response;

import com.sparta.back_office.model.entity.Manager;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpResponseDto {

    private  String email;
    private  String auth;
    private  String team;

    public SignUpResponseDto(Manager manager) {
        this.email = manager.getEmail();
        this.auth = manager.getAuth().getAuthority();
        this.team = manager.getTeam().getTeamName();
    }
}
