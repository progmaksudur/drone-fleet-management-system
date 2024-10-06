package com.spring_assignment.drone_fleet_management_system.model.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAddressDTO {

    private Long id;
    private String area;
    private String policeStation;
    private String postalCode;
    private String city;
    private String country;

}
