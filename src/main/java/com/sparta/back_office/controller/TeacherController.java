package com.sparta.back_office.controller;

import com.sparta.back_office.model.dto.request.TeacherRequestDto;
import com.sparta.back_office.model.dto.response.TeacherResponseDto;
import com.sparta.back_office.model.entity.Teacher;
import com.sparta.back_office.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;
    @PostMapping("")
    public ResponseEntity<TeacherResponseDto> save(@RequestBody TeacherRequestDto teacherRequestDto){
        TeacherResponseDto teacherResponseDto = teacherService.save(teacherRequestDto);
        return ResponseEntity.ok(teacherResponseDto);
    }
}
