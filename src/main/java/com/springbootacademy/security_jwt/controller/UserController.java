package com.springbootacademy.security_jwt.controller;

import com.springbootacademy.security_jwt.entity.User;
import com.springbootacademy.security_jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping({"register-new-user"})
    public User registerNewUser(@RequestBody User user){
        return userService.registerNewUser(user);
    }

    @PostConstruct
    public void initRoleAndUser(){
        userService.initRoleAndUser();
    }

    @GetMapping({"for-admin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This url is only accessible to admin";
    }
    @GetMapping({"for-user"})
    @PreAuthorize("hasRole('User')")
//     @PreAuthorize("hasAnyRole('User','Admin')")

    public String forUser(){
        return "This url is only accessible to user";
    }

}
