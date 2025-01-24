package com.springbootacademy.security_jwt.repo;

import com.springbootacademy.security_jwt.entity.CarImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarImageRepo extends JpaRepository<CarImage, Long> {
}
