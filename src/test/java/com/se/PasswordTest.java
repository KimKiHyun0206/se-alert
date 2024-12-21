package com.se;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.regex.Pattern;

@Slf4j
@SpringBootTest
public class PasswordTest {
    private final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d~!@#$%^&*()+|=]{8,20}$";

    @Test
    protected void password(){
        String password = "aaaaaaaaa123123";

        Assertions.assertTrue(password.matches(PASSWORD_REGEX));
    }
}
