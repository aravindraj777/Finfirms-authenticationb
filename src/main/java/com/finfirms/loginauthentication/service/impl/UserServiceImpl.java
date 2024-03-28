package com.finfirms.loginauthentication.service.impl;

import com.finfirms.loginauthentication.dao.UserRepository;
import com.finfirms.loginauthentication.dto.AuthDto;
import com.finfirms.loginauthentication.dto.AuthResponse;
import com.finfirms.loginauthentication.model.User;
import com.finfirms.loginauthentication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {




    private final UserRepository userRepository;


    private final PasswordEncoder passwordEncoder;



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


    private boolean isUserExists(String username,String email){
        Optional<User> userByUserName = userRepository.findByName(username);
        Optional<User> userByEmail = userRepository.findByEmail(email);

        if(userByUserName.isPresent() || userByEmail.isPresent()){
            return true;
        }
        return false;
    }


}
