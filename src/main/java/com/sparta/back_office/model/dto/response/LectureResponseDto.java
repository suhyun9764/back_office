package com.sparta.back_office.model.dto.response;

import com.sparta.back_office.model.entity.Lecture;
import com.sparta.back_office.model.enums.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class LectureResponseDto {
    private Long id;

    private String lectureName;

    private Long teacherId;

    private int price;

    private Category category;

    private String intro;

    private LocalDate registerDate;

    public LectureResponseDto(Lecture saveLecture) {
        this.id = saveLecture.getId();
        this.lectureName = saveLecture.getLectureName();
        this.teacherId = saveLecture.getTeacher().getId();
        this.price = saveLecture.getPrice();
        this.category = saveLecture.getCategory();
        this.intro = saveLecture.getIntro();
        this.registerDate = saveLecture.getRegisterDate();
    }
}
