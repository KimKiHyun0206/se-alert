package com.se.student.controller;

import com.se.common.dto.ResponseDto;
import com.se.common.dto.ResponseMessage;
import com.se.student.dto.request.StudentCreateRequest;
import com.se.student.dto.request.StudentUpdateRequest;
import com.se.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody StudentCreateRequest request) {
        return ResponseDto.toResponseEntity(
                ResponseMessage.CREATE_SUCCESS_STUDENT,
                studentService.create(request)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readStudent(@PathVariable(value = "id") String id) {
        return ResponseDto.toResponseEntity(
                ResponseMessage.SUCCESS_LOAD_STUDENT_INFORMATION,
                studentService.readById(id)
        );
    }

    @GetMapping
    public ResponseEntity<?> readAllStudent() {
        return ResponseDto.toResponseEntity(
                ResponseMessage.SUCCESS_SEARCH_ALL_STUDENT,
                studentService.readAll()
        );
    }

    @GetMapping("/student-number/{year}")
    public ResponseEntity<?> readStudentByYear(@PathVariable(value = "year") Long year) {
        return ResponseDto.toResponseEntity(
                ResponseMessage.SUCCESS_SEARCH_STUDENT_NUMBER,
                studentService.readByYear(year)
        );
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> readStudentByName(@PathVariable(value = "name") String name) {
        return ResponseDto.toResponseEntity(
                ResponseMessage.SUCCESS_SEARCH_STUDENT_NAME,
                studentService.readByName(name)
        );
    }

    @GetMapping("/permission/{permission}")
    public ResponseEntity<?> readStudentByPermission(@PathVariable(value = "permission") Long permission) {
        return ResponseDto.toResponseEntity(
                ResponseMessage.SUCCESS_SEARCH_STUDENT_PERMISSION,
                studentService.readByPermission(permission)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable(value = "id") String id, @RequestBody StudentUpdateRequest request) {
        return ResponseDto.toResponseEntity(
                ResponseMessage.SUCCESS_UPDATE_STUDENT,
                studentService.update(id, request)
        );
    }
}
