package com.springbootacademy.security_jwt.repo;

import com.springbootacademy.security_jwt.entity.Car;
import com.springbootacademy.security_jwt.entity.CarImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarImageRepo extends JpaRepository<CarImage, Long> {
    List<CarImage> getCarImageByCar(Car car);
}
