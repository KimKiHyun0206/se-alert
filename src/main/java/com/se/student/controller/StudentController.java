package com.se.student.controller;

import com.se.common.dto.ResponseDto;
import com.se.common.dto.ResponseMessage;
import com.se.student.dto.request.StudentCreateRequest;
import com.se.student.dto.request.StudentUpdateRequest;
import com.se.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentRepository studentRepository;

    @PostMapping
    public ResponseEntity<?> create(StudentCreateRequest request) {
        return ResponseDto.toResponseEntity(
                ResponseMessage.CREATE_SUCCESS_STUDENT,
                studentRepository.create(request)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable(value = "id") String id) {
        return ResponseDto.toResponseEntity(
                ResponseMessage.SUCCESS_LOAD_STUDENT_INFORMATION,
                studentRepository.readById(id)
        );
    }

    @GetMapping
    public ResponseEntity<?> readAll() {
        return ResponseDto.toResponseEntity(
                ResponseMessage.SUCCESS_SEARCH_ALL_STUDENT,
                studentRepository.readAll()
        );
    }

    @GetMapping("/student-number/{year}")
    public ResponseEntity<?> readByYear(@PathVariable(value = "year") Long year) {
        return ResponseDto.toResponseEntity(
                ResponseMessage.SUCCESS_SEARCH_STUDENT_NUMBER,
                studentRepository.readByYear(year)
        );
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> readByName(@PathVariable(value = "name") String name) {
        return ResponseDto.toResponseEntity(
                ResponseMessage.SUCCESS_SEARCH_STUDENT_NAME,
                studentRepository.readByName(name)
        );
    }

    @GetMapping("/permission/{permission}")
    public ResponseEntity<?> readByPermission(@PathVariable(value = "permission") Long permission) {
        return ResponseDto.toResponseEntity(
                ResponseMessage.SUCCESS_SEARCH_STUDENT_PERMISSION,
                studentRepository.readByPermission(permission)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") String id, StudentUpdateRequest request) {
        return ResponseDto.toResponseEntity(
                ResponseMessage.SUCCESS_UPDATE_STUDENT,
                studentRepository.update(id, request)
        );
    }
}
