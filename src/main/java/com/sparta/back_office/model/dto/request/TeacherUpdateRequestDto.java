package com.sparta.back_office.model.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TeacherUpdateRequestDto {
    private int career;

    private String company;

    private String phone_number;

    private String intro;
}
