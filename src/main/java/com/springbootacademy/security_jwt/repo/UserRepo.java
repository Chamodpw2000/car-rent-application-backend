package com.springbootacademy.security_jwt.repo;

import com.springbootacademy.security_jwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,String> {


}
