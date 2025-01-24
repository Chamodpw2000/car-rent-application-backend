package com.springbootacademy.security_jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddCarDto {
    private Integer Id;


    private String carName;
    private String carBrand;

    private String carModel;
    private String carYear;
    private String carColor;
    private Integer seates;
    private String fualType;
    private Integer kmpl;
    private List<CarImageDto> images;
    private long rent;
    private String type;


}
