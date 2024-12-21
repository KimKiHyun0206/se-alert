package com.se.student.domain;

import com.se.common.BaseEntity;
import com.se.student.domain.converter.PasswordEncodeConverter;
import com.se.student.domain.vo.Name;
import com.se.student.domain.vo.PhoneNumber;
import com.se.student.dto.response.StudentResponse;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor
@DynamicUpdate
public class Student extends BaseEntity {

    @Id
    private Long id;
    @Convert(converter = PasswordEncodeConverter.class)
    private String password;
    private Name name;
    private PhoneNumber phoneNumber;
    private String aboutMe;
    private Long permission;

    @Builder
    public Student(Long id, String password, Name name, PhoneNumber phoneNumber, String aboutMe, Long permission) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.aboutMe = aboutMe;
        this.permission = permission;
    }

    public StudentResponse toResponse(){
        return StudentResponse
                .builder()
                .id(id)
                .password(password)
                .name(name)
                .phoneNumber(phoneNumber)
                .aboutMe(aboutMe)
                .permission(permission)
                .build();
    }

    public void update(Long id, String password, Name name, PhoneNumber phoneNumber, String aboutMe) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.aboutMe = aboutMe;
    }
}
