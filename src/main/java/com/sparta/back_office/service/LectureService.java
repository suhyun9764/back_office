package com.sparta.back_office.service;

import com.sparta.back_office.exception.lecture.NotFoundLectureException;
import com.sparta.back_office.exception.teacher.NotFoundByTeacherId;
import com.sparta.back_office.model.dto.request.LectureSaveRequestDto;
import com.sparta.back_office.model.dto.request.LectureUpdateRequestDto;
import com.sparta.back_office.model.dto.response.LectureResponseDto;
import com.sparta.back_office.model.entity.Lecture;
import com.sparta.back_office.model.entity.Teacher;
import com.sparta.back_office.repository.LectureRepository;
import com.sparta.back_office.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final TeacherRepository teacherRepository;
    private final LectureRepository lectureRepository;

    public LectureResponseDto save(LectureSaveRequestDto requestDto) {
        // id 확인
        Teacher teacher = teacherRepository.findById(requestDto.getTeacherId())
                .orElseThrow(() -> new NotFoundByTeacherId("해당하는 강사가 존재하지 않습니다"));

        Lecture lecture = new Lecture(requestDto.getLectureName(), teacher, requestDto.getCategory(), requestDto.getPrice(), requestDto.getIntro());
        Lecture savedLecture = lectureRepository.save(lecture);

        return new LectureResponseDto(savedLecture);
    }

    public LectureResponseDto update(Long lectureId, LectureUpdateRequestDto lectureUpdateRequestDto) {
        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(() ->
                new NotFoundLectureException("해당하는 강의가 존재하지 않습니다"));
        lecture.update(lectureUpdateRequestDto);
        Lecture updateLecture = lectureRepository.save(lecture);
        return new LectureResponseDto(updateLecture);
    }
}
