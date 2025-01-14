package com.springbootacademy.security_jwt.service;

import com.springbootacademy.security_jwt.entity.Role;
import com.springbootacademy.security_jwt.entity.User;
import com.springbootacademy.security_jwt.repo.RoleRepo;
import com.springbootacademy.security_jwt.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerNewUser(User user) { return userRepo.save(user);}


    public void initRoleAndUser(){
        Role userRole = new Role();
        Role adminRole = new Role();


        if(!roleRepo.existsById("Admin")){
            adminRole.setRoleName("Admin");
            adminRole.setRoleDescription("Admin Role");
            roleRepo.save(adminRole);


        }

        if(!roleRepo.existsById("User")) {
            userRole.setRoleName("User");
            userRole.setRoleDescription("User Role");
            roleRepo.save(userRole);
        }


        if(!userRepo.existsById("admin@mail.com")) {

            User user = new User();
            user.setUserEmail("admin@mail.com");
            user.setUserPassword(getEncodedPassword("admin"));
            user.setUserFirstName("admin");
            user.setUserLastName("admin");
            user.setUserAddress("admin address");
            user.setNic("admin nic");


            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(adminRole);

            user.setRole(adminRoles);
            userRepo.save(user);
        }


        if(!userRepo.existsById("user@mail.com")) {

            User user = new User();
            user.setUserEmail("user@mail.com");
            user.setUserPassword(getEncodedPassword("user"));
            user.setUserFirstName("user");
            user.setUserLastName("user");
            user.setNic("user nic");
            user.setUserAddress("user address");
            Set<Role> userRoles = new HashSet<>();
            userRoles.add(userRole);

            user.setRole(userRoles);
            userRepo.save(user);
        }









    }

    public String getEncodedPassword(String password){
        return  passwordEncoder.encode(password);
    }
}
