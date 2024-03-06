package com.sparta.back_office.controller;

import com.sparta.back_office.model.dto.request.TeacherUpdateRequestDto;
import com.sparta.back_office.model.dto.request.TeacherSaveRequestDto;
import com.sparta.back_office.model.dto.response.TeacherResponseDto;
import com.sparta.back_office.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;
    @PostMapping("")
    public ResponseEntity<TeacherResponseDto> save(@RequestBody TeacherSaveRequestDto teacherSaveRequestDto){
        TeacherResponseDto teacherResponseDto = teacherService.save(teacherSaveRequestDto);
        return ResponseEntity.ok(teacherResponseDto);
    }

    @PutMapping("/{teacherId}")
    public ResponseEntity<TeacherResponseDto> update(@PathVariable Long teacherId, @RequestBody TeacherUpdateRequestDto teacherUpdateRequestDto){
        TeacherResponseDto teacherResponseDto = teacherService.update(teacherId,teacherUpdateRequestDto);
        return ResponseEntity.ok(teacherResponseDto);
    }

    @GetMapping("/{teacherId}")
    public ResponseEntity<TeacherResponseDto> findById(@PathVariable Long teacherId){
        TeacherResponseDto teacherResponseDto = teacherService.findById(teacherId);
        return ResponseEntity.ok(teacherResponseDto);
    }

    @DeleteMapping("/{teacherId}")
    public ResponseEntity<String> deleteTeacherAndLectures(@PathVariable Long teacherId) {
        Long deleteTeacherId = teacherService.deleteTeacherAndLectures(teacherId);

        return ResponseEntity.ok("<"+deleteTeacherId+"> 강사 및 해당 강사의 강의 삭제");

    }

}
