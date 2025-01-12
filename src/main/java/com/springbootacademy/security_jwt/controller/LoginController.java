package com.springbootacademy.security_jwt.controller;

import com.springbootacademy.security_jwt.dto.LoginRequest;
import com.springbootacademy.security_jwt.dto.LoginResponse;
import com.springbootacademy.security_jwt.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private JwtService jwtService;

    @PostMapping({"/authentication"})
    public LoginResponse createJwtTokenAndLogin(@RequestBody LoginRequest loginRequest) throws Exception{
        System.out.println(loginRequest);
        return jwtService.createJwtToken(loginRequest);


    }
}
