package com.springbootacademy.security_jwt.service;

import com.springbootacademy.security_jwt.dto.LoginRequest;
import com.springbootacademy.security_jwt.dto.LoginResponse;
import com.springbootacademy.security_jwt.entity.User;
import com.springbootacademy.security_jwt.repo.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.springbootacademy.security_jwt.util.JwtUtil;

import java.util.HashSet;
import java.util.Set;

@Component
@Service
public class JwtService implements UserDetailsService {


    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String useremail) throws UsernameNotFoundException {
        User user = userRepo.findById(useremail).get();

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getUserEmail(),
                    user.getUserPassword(),
                    getAuthority(user)

            );

        }else {
            throw new UsernameNotFoundException("User not found with username " + useremail);
        }


    }

    private Set getAuthority(User user){
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRole().forEach(role-> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));


        } );
        return authorities;


    }

    public LoginResponse createJwtToken(LoginRequest loginRequest) throws Exception{

            String userEmail = loginRequest.getUserEmail();
            String userPassword = loginRequest.getUserPassword();

            authenticate(userEmail,userPassword);



            UserDetails userDetails = loadUserByUsername(userEmail);



            String newGeneratedToken = jwtUtil.generateToken(userDetails);
            User user = userRepo.findById(userEmail).get();

            LoginResponse loginResponse = new LoginResponse(
                    user,
                    newGeneratedToken
            );
            return loginResponse;





    }

    private void authenticate(String userEmail, String userPassword) throws Exception{

        try{

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userEmail, userPassword));







        }catch(BadCredentialsException e){

            throw new Exception("Invalid username or password",e);



        }
    }
}
