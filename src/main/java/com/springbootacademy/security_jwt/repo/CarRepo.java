package com.springbootacademy.security_jwt.repo;

import com.springbootacademy.security_jwt.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepo extends JpaRepository<Car, Integer> {
}
