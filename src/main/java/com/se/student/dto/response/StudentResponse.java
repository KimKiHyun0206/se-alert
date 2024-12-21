package com.se.student.dto.response;

import com.se.student.domain.vo.Name;
import com.se.student.domain.vo.PhoneNumber;
import lombok.Builder;
import lombok.Data;

@Data
public class StudentResponse {
    private String id;
    private String password;
    private String name;
    private String phoneNumber;
    private String aboutMe;
    private Long permission;


    @Builder
    public StudentResponse(String id, String password, Name name, PhoneNumber phoneNumber, String aboutMe,Long permission) {
        this.id = id;
        this.password = password;
        this.name = name.getName();
        this.phoneNumber = phoneNumber.getPhoneNumber();
        this.aboutMe = aboutMe;
        this.permission = permission;
    }
}
