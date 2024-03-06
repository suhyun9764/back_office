package com.sparta.back_office.controller;

import com.sparta.back_office.model.dto.request.LectureSaveRequestDto;
import com.sparta.back_office.model.dto.request.LectureUpdateRequestDto;
import com.sparta.back_office.model.dto.response.LectureResponseDto;
import com.sparta.back_office.model.enums.Category;
import com.sparta.back_office.service.LectureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LectureController {
    private final LectureService lectureService;
    @PostMapping("/lectures")
    public ResponseEntity<LectureResponseDto> save(@RequestBody @Valid LectureSaveRequestDto lectureSaveRequestDto){
        LectureResponseDto lectureResponseDto = lectureService.save(lectureSaveRequestDto);
        return ResponseEntity.ok(lectureResponseDto);
    }

    @GetMapping("/lectures")
    public ResponseEntity<List<LectureResponseDto>> findByCategory(@RequestParam @Valid Category category){
        List<LectureResponseDto> lectures = lectureService.findByCategory(category);
        return ResponseEntity.ok(lectures);
    }



    @PutMapping("/lectures/{lectureId}")
    public ResponseEntity<LectureResponseDto> update(@PathVariable Long lectureId,@RequestBody LectureUpdateRequestDto lectureUpdateRequestDto){
        LectureResponseDto lectureResponseDto = lectureService.update(lectureId,lectureUpdateRequestDto);
        return ResponseEntity.ok(lectureResponseDto);
    }

    @GetMapping("/lectures/{lectureId}")
    public ResponseEntity<LectureResponseDto> findById(@PathVariable Long lectureId){
        LectureResponseDto lectureResponseDto = lectureService.findById(lectureId);
        return ResponseEntity.ok(lectureResponseDto);
    }

    @DeleteMapping("/lectures/{lectureId}")
    public ResponseEntity<String> delete(@PathVariable Long lectureId){
        Long deleteId = lectureService.delete(lectureId);
        return ResponseEntity.ok("<"+deleteId+">강의가 삭제되었습니다");
    }

    @GetMapping("/teachers/{teacherId}/lectures")
    public ResponseEntity<List<LectureResponseDto>> findByTeacher(@PathVariable Long teacherId){
        List<LectureResponseDto> lectures = lectureService.findByTeacher(teacherId);
        return ResponseEntity.ok(lectures);
    }

}
