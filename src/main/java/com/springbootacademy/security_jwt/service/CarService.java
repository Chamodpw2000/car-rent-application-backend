package com.springbootacademy.security_jwt.service;

import com.springbootacademy.security_jwt.dto.AddCarDto;
import com.springbootacademy.security_jwt.entity.Car;
import com.springbootacademy.security_jwt.entity.CarImage;
import com.springbootacademy.security_jwt.repo.CarImageRepo;
import com.springbootacademy.security_jwt.repo.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    @Autowired
    private CarRepo carRepo;

    @Autowired
    private CarImageRepo carImageRepo;



    public String addCar(AddCarDto addCarDto) {




            try {
                // Create and save the car first
                Car car = new Car();
                car.setCarName(addCarDto.getCarName());
                car.setCarBrand(addCarDto.getCarBrand());
                car.setCarModel(addCarDto.getCarModel());
                car.setCarYear(addCarDto.getCarYear());
                car.setCarColor(addCarDto.getCarColor());
                car.setSeates(addCarDto.getSeates());
                car.setFualType(addCarDto.getFualType());
                car.setKmpl(addCarDto.getKmpl());
                car.setRent(addCarDto.getRent());
                car.setType(addCarDto.getType());

                // Save the car first to get the ID
                Car savedCar = carRepo.save(car);

                // Create and associate images
                if (addCarDto.getImages() != null && !addCarDto.getImages().isEmpty()) {
                    List<CarImage> carImages = addCarDto.getImages().stream()
                            .map(imageDto -> {
                                CarImage carImage = new CarImage();
                                carImage.setImageUrl(imageDto.getImageUrl());
                                carImage.setCar(savedCar);
                                return carImage;
                            })
                            .collect(Collectors.toList());

                    carImageRepo.saveAll(carImages);


                    savedCar.setImages(carImages);
                }

                return "Car added successfully";
            } catch (Exception e) {
                System.out.println(e);
                return "Error Saving Car: " + e.getMessage();
            }
        }
}
