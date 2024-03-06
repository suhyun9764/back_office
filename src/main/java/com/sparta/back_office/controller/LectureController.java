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
@RequestMapping("/api/lectures")
@RequiredArgsConstructor
public class LectureController {
    private final LectureService lectureService;
    @PostMapping("")
    public ResponseEntity<LectureResponseDto> save(@RequestBody @Valid LectureSaveRequestDto lectureSaveRequestDto){
        LectureResponseDto lectureResponseDto = lectureService.save(lectureSaveRequestDto);
        return ResponseEntity.ok(lectureResponseDto);
    }

    @GetMapping("")
    public ResponseEntity<List<LectureResponseDto>> findByCategory(@RequestParam @Valid Category category){
        List<LectureResponseDto> lectures = lectureService.findByCategory(category);
        return ResponseEntity.ok(lectures);
    }

    @PutMapping("/{lectureId}")
    public ResponseEntity<LectureResponseDto> update(@PathVariable Long lectureId,@RequestBody LectureUpdateRequestDto lectureUpdateRequestDto){
        LectureResponseDto lectureResponseDto = lectureService.update(lectureId,lectureUpdateRequestDto);
        return ResponseEntity.ok(lectureResponseDto);
    }

    @GetMapping("/{lectureId}")
    public ResponseEntity<LectureResponseDto> findById(@PathVariable Long lectureId){
        LectureResponseDto lectureResponseDto = lectureService.findById(lectureId);
        return ResponseEntity.ok(lectureResponseDto);
    }


}
