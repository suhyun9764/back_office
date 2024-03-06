package com.sparta.back_office.controller;

import com.sparta.back_office.exception.manager.SignUpInputException;
import com.sparta.back_office.model.dto.request.SignInRequestDto;
import com.sparta.back_office.model.dto.request.SignUpRequestDto;
import com.sparta.back_office.service.ManagerService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ManagerController {
    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody @Valid SignUpRequestDto requestDto, BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if (fieldErrors.size() > 0) {
            String message = fieldErrors.stream().findFirst().get().getField() + "값을 확인해주세요";
            throw new SignUpInputException(message);
        }
        return ResponseEntity.ok(managerService.signUp(requestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody SignInRequestDto requestDto, HttpServletResponse response){
        return ResponseEntity.ok(managerService.login(requestDto,response));
    }

}
