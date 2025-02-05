package com.springbootacademy.security_jwt.service;

import com.springbootacademy.security_jwt.dto.AddCarDto;
import com.springbootacademy.security_jwt.dto.CarImageDto;
import com.springbootacademy.security_jwt.entity.Car;
import com.springbootacademy.security_jwt.entity.CarImage;
import com.springbootacademy.security_jwt.repo.CarImageRepo;
import com.springbootacademy.security_jwt.repo.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {

    @Autowired
    private CarRepo carRepo;

    @Autowired
    private CarImageRepo carImageRepo;



    public String addCar(AddCarDto addCarDto) {




            try {

                Car car = new Car();
                car.setId(addCarDto.getId());
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
                car.setAvailability(true);
                car.setCapacity(addCarDto.getCapacity());

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


        public List<AddCarDto> getCars() {

        List<Car> cars = carRepo.findAll();
        List<AddCarDto> carDtos = new ArrayList<>();
        for (Car car : cars) {

            AddCarDto addCarDto = new AddCarDto();
            addCarDto.setId(car.getId());
            addCarDto.setCarName(car.getCarName());
            addCarDto.setCarBrand(car.getCarBrand());
            addCarDto.setCarModel(car.getCarModel());
            addCarDto.setCarYear(car.getCarYear());
            addCarDto.setCarColor(car.getCarColor());
            addCarDto.setSeates(car.getSeates());
            addCarDto.setFualType(car.getFualType());
            addCarDto.setKmpl(car.getKmpl());
            addCarDto.setRent(car.getRent());
            addCarDto.setType(car.getType());
            addCarDto.setCapacity(car.getCapacity());
            addCarDto.setAvailability(car.isAvailability());
            addCarDto.setCapacity(car.getCapacity());

            List<CarImage> carImages = carImageRepo.getCarImageByCar(car);

            List<CarImageDto> carImageDtos = new ArrayList<>();

            for (CarImage carImage : carImages) {
                CarImageDto carImageDto = new CarImageDto();
                carImageDto.setImageUrl(carImage.getImageUrl());
                carImageDtos.add(carImageDto);
            }

            addCarDto.setImages(carImageDtos);


            carDtos.add(addCarDto);
        }


        return carDtos;



        }


        public AddCarDto getCar(int id) {
        AddCarDto addCarDto = new AddCarDto();
        Optional<Car> car = carRepo.findById(id);

        if (car.isPresent()) {
            addCarDto.setId(car.get().getId());
            addCarDto.setCarName(car.get().getCarName());
            addCarDto.setCarBrand(car.get().getCarBrand());
            addCarDto.setCarModel(car.get().getCarModel());
            addCarDto.setCarYear(car.get().getCarYear());
            addCarDto.setCarColor(car.get().getCarColor());
            addCarDto.setSeates(car.get().getSeates());
            addCarDto.setFualType(car.get().getFualType());
            addCarDto.setKmpl(car.get().getKmpl());
            addCarDto.setRent(car.get().getRent());
            addCarDto.setType(car.get().getType());
            addCarDto.setCapacity(car.get().getCapacity());
            addCarDto.setAvailability(car.get().isAvailability());
            List<CarImage> carImages = carImageRepo.getCarImageByCar(car.get());
            List<CarImageDto> carImageDtos = new ArrayList<>();
            for (CarImage carImage : carImages) {
                CarImageDto carImageDto = new CarImageDto();
                carImageDto.setImageUrl(carImage.getImageUrl());
                carImageDtos.add(carImageDto);

            }
            addCarDto.setImages(carImageDtos);
        }
        return addCarDto;




        }

        @Transactional
        public String deleteImages(int id){
            Optional<Car> car = carRepo.findById(id);
            String status = carImageRepo.deleteCarImagesByCar(car);
            return status;


        };

}
