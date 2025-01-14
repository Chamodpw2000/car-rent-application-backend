package com.springbootacademy.security_jwt.dto;

import com.springbootacademy.security_jwt.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginResponse {
    private User user;
    private  String jwtToken;


}
