package com.sparta.back_office.model.entity;

import com.sparta.back_office.model.dto.request.TeacherSaveRequestDto;
import com.sparta.back_office.model.dto.request.TeacherUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lecture> lectures = new ArrayList<>();

    @Column
    private String intro;

    public Teacher(TeacherSaveRequestDto requestDto) {
        this.name = requestDto.getName();
        this.career = requestDto.getCareer();
        this.company = requestDto.getCompany();
        this.phone_number = requestDto.getPhone_number();
        this.intro = requestDto.getIntro();
    }

    public void update(TeacherUpdateRequestDto teacherUpdateRequestDto) {
        this.career = teacherUpdateRequestDto.getCareer();
        this.company = teacherUpdateRequestDto.getCompany();
        this.intro = teacherUpdateRequestDto.getIntro();
        this.phone_number = teacherUpdateRequestDto.getPhone_number();
    }
}
