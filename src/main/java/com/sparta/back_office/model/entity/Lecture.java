package com.sparta.back_office.model.entity;

import com.sparta.back_office.model.dto.request.LectureUpdateRequestDto;
import com.sparta.back_office.model.enums.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lectures")
@NoArgsConstructor
@Getter
public class Lecture extends DateValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String lectureName;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Category category;

    @Column(nullable = false)
    private String intro;

    public Lecture(String lectureName, Teacher teacher, Category category, int price, String intro) {
        this.lectureName = lectureName;
        this.teacher = teacher;
        this.category = category;
        this.price = price;
        this.intro = intro;
    }

    public void update(LectureUpdateRequestDto lectureUpdateRequestDto) {
        this.lectureName = lectureUpdateRequestDto.getLectureName();
        this.price = lectureUpdateRequestDto.getPrice();
        this.intro = lectureUpdateRequestDto.getIntro();
        this.category = lectureUpdateRequestDto.getCategory();
    }
}
