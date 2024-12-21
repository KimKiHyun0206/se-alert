package com.se.auth.service;

import com.se.auth.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final LoginRepository loginRepository;
    public UserDetails login(String username, String password) {
        UserDetails userDetails = loginRepository.loadUserByUsername(username);
        if(userDetails.getPassword().equals(password)) {
            return userDetails;
        }

        return null;
    }
}
