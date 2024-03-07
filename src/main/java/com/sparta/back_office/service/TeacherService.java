package com.sparta.back_office.service;

import com.sparta.back_office.exception.teacher.NotFoundByTeacherId;
import com.sparta.back_office.model.dto.request.TeacherSaveRequestDto;
import com.sparta.back_office.model.dto.request.TeacherUpdateRequestDto;
import com.sparta.back_office.model.dto.response.TeacherResponseDto;
import com.sparta.back_office.model.entity.Teacher;
import com.sparta.back_office.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.sparta.back_office.constants.TeacherConstants.TEACHER_NOT_EXIST;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;

    @Transactional
    public TeacherResponseDto save(TeacherSaveRequestDto teacherSaveRequestDto) {
        Teacher teacher = teacherRepository.save(new Teacher(teacherSaveRequestDto));
        return new TeacherResponseDto(teacher);
    }

    public TeacherResponseDto findById(Long teacherId) {
        Teacher teacher = getTeacher(teacherId);
        return new TeacherResponseDto(teacher);
    }

    @Transactional
    public TeacherResponseDto update(Long teacherId, TeacherUpdateRequestDto teacherUpdateRequestDto) {
        Teacher teacher = getTeacher(teacherId);
        teacher.update(teacherUpdateRequestDto);
        Teacher updateTeacher = teacherRepository.save(teacher);

        return new TeacherResponseDto(updateTeacher);

    }

    @Transactional
    public Long deleteTeacherAndLectures(Long teacherId) {
        Teacher teacher = getTeacher(teacherId);
        teacherRepository.delete(teacher);
        return teacherId;
    }

    private Teacher getTeacher(Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() ->
                new NotFoundByTeacherId(TEACHER_NOT_EXIST));
        return teacher;
    }
}
