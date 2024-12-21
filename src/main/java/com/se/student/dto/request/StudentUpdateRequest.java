package com.se.student.dto.request;

import com.se.student.domain.vo.PhoneNumber;
import lombok.Data;

@Data
public class StudentUpdateRequest {
    private String id;
    private String password;
    private String name;
    private String phoneNumber;
    private String aboutMe;
}
