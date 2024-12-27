package com.se.student.service;

import com.se.student.domain.Student;
import com.se.student.dto.request.StudentCreateRequest;
import com.se.student.dto.request.StudentUpdateRequest;
import com.se.student.dto.response.StudentResponse;
import com.se.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentResponse create(StudentCreateRequest student){
        return studentRepository.create(student).toResponse();
    }

    public StudentResponse readById(String id){
        return studentRepository.readById(id).toResponse();
    }

    public List<StudentResponse> readAll(){
        return studentRepository.readAll().stream().map(Student::toResponse).toList();
    }

    public List<StudentResponse> readByName(String name){
        return studentRepository.readByName(name).stream().map(Student::toResponse).toList();
    }

    public List<StudentResponse> readByYear(Long year){
        return studentRepository.readByYear(year).stream().map(Student::toResponse).toList();
    }

    public List<StudentResponse> readByPermission(Long permission){
        return studentRepository.readByPermission(permission).stream().map(Student::toResponse).toList();
    }

    public StudentResponse update(String id, StudentUpdateRequest studentUpdateRequest){
        return studentRepository.update(id, studentUpdateRequest).toResponse();
    }
}
