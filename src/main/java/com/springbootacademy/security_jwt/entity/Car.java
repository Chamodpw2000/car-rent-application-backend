package com.springbootacademy.security_jwt.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Car {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;









    private String carName;
    private String carBrand;

    private String carModel;
    private String carYear;
    private String carColor;
    private Integer seates;
    private String fualType;
    private Integer kmpl;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarImage> images = new ArrayList<>();
    private long rent;
    private String type;
    private boolean availability;
    private long capacity;


}
