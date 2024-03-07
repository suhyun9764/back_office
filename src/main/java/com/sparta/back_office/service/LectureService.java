package com.sparta.back_office.service;

import com.sparta.back_office.exception.lecture.NotFoundLectureException;
import com.sparta.back_office.exception.teacher.NotFoundByTeacherId;
import com.sparta.back_office.model.dto.request.LectureSaveRequestDto;
import com.sparta.back_office.model.dto.request.LectureUpdateRequestDto;
import com.sparta.back_office.model.dto.response.LectureResponseDto;
import com.sparta.back_office.model.entity.Lecture;
import com.sparta.back_office.model.entity.Teacher;
import com.sparta.back_office.model.enums.Category;
import com.sparta.back_office.repository.LectureRepository;
import com.sparta.back_office.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.sparta.back_office.constants.LectureConstants.LECTURE_NOT_EXIST;
import static com.sparta.back_office.constants.TeacherConstants.TEACHER_NOT_EXIST;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final TeacherRepository teacherRepository;
    private final LectureRepository lectureRepository;

    @Transactional
    public LectureResponseDto save(LectureSaveRequestDto requestDto) {
        Teacher teacher = getTeacher(requestDto);
        Lecture lecture = new Lecture(requestDto.getLectureName(), teacher, requestDto.getCategory(), requestDto.getPrice(), requestDto.getIntro());
        Lecture savedLecture = lectureRepository.save(lecture);

        return new LectureResponseDto(savedLecture);
    }

    public List<LectureResponseDto> findByCategory(Category category) {
        List<Lecture> lectures = lectureRepository.findAllByCategoryOrderByRegisterDateDesc(category);
        return lectures.stream().map(LectureResponseDto::new).toList();
    }
    @Transactional
    public LectureResponseDto update(Long lectureId, LectureUpdateRequestDto lectureUpdateRequestDto) {
        Lecture lecture = getLecture(lectureId);
        lecture.update(lectureUpdateRequestDto);
        Lecture updateLecture = lectureRepository.save(lecture);
        return new LectureResponseDto(updateLecture);
    }
    @Transactional
    public Long delete(Long lectureId) {
        Lecture lecture = getLecture(lectureId);
        lectureRepository.delete(lecture);
        return lectureId;
    }

    public LectureResponseDto findById(Long lectureId) {
        Lecture lecture = getLecture(lectureId);
        return new LectureResponseDto(lecture);
    }

    public List<LectureResponseDto> findByTeacher(Long teacherId) {
        getLecture(teacherId);
        return lectureRepository.findAllByTeacherIdOrderByRegisterDateDesc(teacherId).stream()
                .map(LectureResponseDto::new).toList();
    }

    private Teacher getTeacher(LectureSaveRequestDto requestDto) {
        Teacher teacher = teacherRepository.findById(requestDto.getTeacherId())
                .orElseThrow(() -> new NotFoundByTeacherId(TEACHER_NOT_EXIST));
        return teacher;
    }

    private Lecture getLecture(Long lectureId) {
        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(() ->
                new NotFoundLectureException(LECTURE_NOT_EXIST));
        return lecture;
    }




}
