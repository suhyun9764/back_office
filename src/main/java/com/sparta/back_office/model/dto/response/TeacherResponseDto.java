package com.sparta.back_office.model.dto.response;

import com.sparta.back_office.model.entity.Teacher;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TeacherResponseDto {
    private Long id;
    private String name;

    private int career;

    private String company;

    private String phone_number;

    private String intro;

    public TeacherResponseDto(Teacher teacher) {
        this.id = teacher.getId();
        this.name = teacher.getName();
        this.career = teacher.getCareer();
        this.company = teacher.getCompany();
        this.phone_number = teacher.getPhone_number();
        this.intro = teacher.getIntro();
    }
}
