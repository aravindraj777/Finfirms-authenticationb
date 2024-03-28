package com.finfirms.loginauthentication.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthDto {

    private Long id;
    private String firstName;
    private String LastName;
    private String name;
    private String phone;
    private String email;
    private String password;

}
