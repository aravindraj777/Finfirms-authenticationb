package com.finfirms.loginauthentication.service.impl;

import com.finfirms.loginauthentication.dao.UserRepository;
import com.finfirms.loginauthentication.dto.AuthDto;
import com.finfirms.loginauthentication.dto.AuthResponse;
import com.finfirms.loginauthentication.dto.LoginRequest;
import com.finfirms.loginauthentication.dto.LoginResponse;
import com.finfirms.loginauthentication.model.User;
import com.finfirms.loginauthentication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {




    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;



    @Override
    public ResponseEntity<AuthResponse> registerUser(AuthDto authDto) {

        if(isUserExists(authDto.getName(),authDto.getEmail())){
           return ResponseEntity.badRequest().body(
                   AuthResponse.builder()
                           .status(HttpStatus.CONFLICT.value())
                           .message("User Already exists")
                           .build());
        }
        else{
          User user = User.builder()
                    .firstName(authDto.getFirstName())
                    .lastName(authDto.getLastName())
                    .email(authDto.getEmail())
                    .name(authDto.getName())
                    .phone(authDto.getPhone())
                    .password(passwordEncoder.encode(authDto.getPassword()))
                    .build();

          userRepository.save(user);

            return ResponseEntity.ok().body(AuthResponse.builder().
                    status(HttpStatus.CREATED.value())
                    .message("User Registered")
                    .build());

        }
    }

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {
        User user = userRepository.findByName(loginRequest.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        try {
            // Attempt to authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getName(), loginRequest.getPassword()));

            // If authentication is successful
            if (authentication.isAuthenticated()) {
                LoginResponse response = LoginResponse
                        .builder()
                        .user(user)
                        .build();
                return ResponseEntity.ok(response);
            }
        } catch (AuthenticationException e) {
            // Authentication failed
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }


        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }



    private boolean isUserExists(String username,String email){
        Optional<User> userByUserName = userRepository.findByName(username);
        Optional<User> userByEmail = userRepository.findByEmail(email);

        return userByUserName.isPresent() || userByEmail.isPresent();
    }


}
