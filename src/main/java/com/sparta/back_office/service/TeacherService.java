package com.sparta.back_office.service;

import com.sparta.back_office.model.dto.request.TeacherRequestDto;
import com.sparta.back_office.model.dto.response.TeacherResponseDto;
import com.sparta.back_office.model.entity.Teacher;
import com.sparta.back_office.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherResponseDto save(TeacherRequestDto teacherRequestDto) {
        Teacher teacher = teacherRepository.save(new Teacher(teacherRequestDto));
        return new TeacherResponseDto(teacher);
    }
}
