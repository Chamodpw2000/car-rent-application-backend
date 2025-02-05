package com.springbootacademy.security_jwt.controller;

import com.springbootacademy.security_jwt.dto.AddCarDto;
import com.springbootacademy.security_jwt.entity.Car;
import com.springbootacademy.security_jwt.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


@GetMapping({"/getcars"})
    public List<AddCarDto> getCars() {

        List<AddCarDto> cars = carService.getCars();
                return cars;
}


@GetMapping({"/getcar/{id}"})
public AddCarDto getCar(@PathVariable int id) {

   AddCarDto addCarDto = carService.getCar(id);

   return addCarDto;
};
@DeleteMapping({"/deleteimages/{id}"})
@PreAuthorize("hasRole('Admin')")
public String deleteImages(@PathVariable int id){

    return carService.deleteImages(id);
}

}
