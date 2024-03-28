package com.finfirms.loginauthentication.service;

import com.finfirms.loginauthentication.dto.AuthDto;
import com.finfirms.loginauthentication.dto.AuthResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {


       ResponseEntity<AuthResponse> registerUser(AuthDto authDto);
}
