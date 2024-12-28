package com.se.testapi;

import com.se.common.dto.ResponseDto;
import com.se.common.dto.ResponseMessage;
import com.se.jwt.dto.TokenDto;
import com.se.jwt.provider.TokenProvider;
import com.se.student.dto.request.StudentCreateRequest;
import com.se.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/test")
public class TestTokenController {
    private final StudentService studentService;
    private final AuthenticationProvider authenticationManager;
    private final TokenProvider tokenProvider;

    @GetMapping("/token")
    public ResponseEntity<?> getToken() {
        StudentCreateRequest studentCreateRequest = new StudentCreateRequest();
        studentCreateRequest.setId("202158012");
        studentCreateRequest.setName("강상문");

        studentService.create(studentCreateRequest);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(studentCreateRequest.getId(), "123456789a");

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = tokenProvider.createAccessToken(authentication);
        String refreshToken = tokenProvider.createRefreshToken(authentication);

        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS, new TokenDto(accessToken, refreshToken));
    }
}
