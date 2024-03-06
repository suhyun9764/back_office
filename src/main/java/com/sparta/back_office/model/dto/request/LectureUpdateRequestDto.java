package com.sparta.back_office.model.dto.request;

import com.sparta.back_office.model.enums.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LectureUpdateRequestDto {

    private String lectureName;

    private int price;

    private Category category;

    private String intro;

}
