package com.se.student.controller;

import com.se.common.dto.ResponseDto;
import com.se.common.dto.ResponseMessage;
import com.se.student.dto.request.StudentUpdateRequest;
import com.se.student.repository.StudentUpdateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentUpdateController {

    private final StudentUpdateRepository studentRepository;

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") String id, StudentUpdateRequest request) {
        return ResponseDto.toResponseEntity(
                ResponseMessage.SUCCESS_UPDATE_STUDENT,
                studentRepository.update(id, request)
        );
    }
}
