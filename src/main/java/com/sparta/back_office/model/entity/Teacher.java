package com.sparta.back_office.model.entity;

import com.sparta.back_office.model.dto.request.TeacherRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "teachers")
@NoArgsConstructor
@Getter
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private int career;

    @Column
    private String company;

    @Column
    private String phone_number;

    @Column
    private String intro;

    public Teacher(TeacherRequestDto requestDto) {
        this.name = requestDto.getName();
        this.career = requestDto.getCareer();
        this.company = requestDto.getCompany();
        this.phone_number = requestDto.getPhone_number();
        this.intro = requestDto.getIntro();
    }
}
