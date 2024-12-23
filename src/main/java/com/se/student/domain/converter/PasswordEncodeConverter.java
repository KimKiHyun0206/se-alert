package com.se.student.domain.converter;

import com.se.student.domain.vo.Password;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;


@Slf4j
@Converter
@RequiredArgsConstructor
public class PasswordEncodeConverter implements AttributeConverter<String, String> {
    private final PasswordEncoder passwordEncoder;

    @Override
    public String convertToDatabaseColumn(String attribute) {
        var password = new Password(attribute);
        password.setEncodePassword(passwordEncoder.encode(attribute));

        return password.getPassword();
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return dbData;
    }
}