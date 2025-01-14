package com.springbootacademy.security_jwt.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    private String userEmail;

    private String nic;

    private String mobileNumber;

    private String userFirstName;

    private String userLastName;

    private String userPassword;

    private String userAddress;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="USER_ROLE",
    joinColumns = {
            @JoinColumn(name = "USER_ID")
    },
    inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID")
    })
    private Set<Role> role;









}
