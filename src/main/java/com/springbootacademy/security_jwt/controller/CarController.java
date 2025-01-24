package com.springbootacademy.security_jwt.controller;

import com.springbootacademy.security_jwt.dto.AddCarDto;
import com.springbootacademy.security_jwt.entity.Car;
import com.springbootacademy.security_jwt.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {

    @Autowired
    private CarService carService;



    @PostMapping({"/addcar"})
    @PreAuthorize("hasRole('Admin')")
    public String addCar(@RequestBody AddCarDto car) {
        String string = carService.addCar(car);
        return string;
}



}
