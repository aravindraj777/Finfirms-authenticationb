package com.finfirms.loginauthentication.controller;

import com.finfirms.loginauthentication.dto.AuthDto;
import com.finfirms.loginauthentication.dto.AuthResponse;
import com.finfirms.loginauthentication.dto.LoginRequest;
import com.finfirms.loginauthentication.dto.LoginResponse;
import com.finfirms.loginauthentication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200/")
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody AuthDto authDto){
        return userService.registerUser(authDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        return userService.login(loginRequest);
    }



}
