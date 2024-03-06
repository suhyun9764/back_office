package com.sparta.back_office.model.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TeacherRequestDto {
    private String name;

    private int career;

    private String company;

    private String phone_number;

    private String intro;

}
