package com.springbootacademy.security_jwt.repo;

import com.springbootacademy.security_jwt.entity.Car;
import com.springbootacademy.security_jwt.entity.CarImage;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface CarImageRepo extends JpaRepository<CarImage, Long> {
    List<CarImage> getCarImageByCar(Car car);

    @Transactional
     String deleteCarImagesByCar(Optional<Car> car);


}
