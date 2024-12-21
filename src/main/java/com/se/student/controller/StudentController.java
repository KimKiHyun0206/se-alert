package com.se.student.controller;

import com.se.common.dto.ResponseDto;
import com.se.common.dto.ResponseMessage;
import com.se.student.dto.request.StudentRegisterRequest;
import com.se.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentRepository repository;

    @PostMapping
    public ResponseEntity<?> register(StudentRegisterRequest request) {
        return ResponseDto.toResponseEntity(
                ResponseMessage.CREATE_SUCCESS_STUDENT,
                repository.save(request)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudent(@PathVariable(value = "id") String id) {
        return ResponseDto.toResponseEntity(
                ResponseMessage.SUCCESS_LOAD_STUDENT_INFORMATION,
                repository.findById(id)
        );
    }

    @GetMapping
    public ResponseEntity<?> getAllStudents() {
        return ResponseDto.toResponseEntity(
                ResponseMessage.SUCCESS_SEARCH_ALL_STUDENT,
                repository.findAll()
        );
    }

    @GetMapping("/student-number/{year}")
    public ResponseEntity<?> getStudentByYear(@PathVariable(value = "year") Long year) {
        return ResponseDto.toResponseEntity(
                ResponseMessage.SUCCESS_SEARCH_STUDENT_NUMBER,
                repository.findByYear(year)
        );
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getStudentByName(@PathVariable(value = "name") String name) {
        return ResponseDto.toResponseEntity(
                ResponseMessage.SUCCESS_SEARCH_STUDENT_NAME,
                repository.findByName(name)
        );
    }

    @GetMapping("/permission/{permission}")
    public ResponseEntity<?> getStudentByPermission(@PathVariable(value = "permission") Long permission) {
        return ResponseDto.toResponseEntity(
                ResponseMessage.SUCCESS_SEARCH_STUDENT_PERMISSION,
                repository.findByPermission(permission)
        );
    }
}
