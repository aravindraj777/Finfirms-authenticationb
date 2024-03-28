package com.finfirms.loginauthentication.service;

import com.finfirms.loginauthentication.dto.AuthDto;
import com.finfirms.loginauthentication.dto.AuthResponse;
import com.finfirms.loginauthentication.dto.LoginRequest;
import com.finfirms.loginauthentication.dto.LoginResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {


    ResponseEntity<AuthResponse> registerUser(AuthDto authDto);

    ResponseEntity<LoginResponse> login(LoginRequest loginRequest);
}
