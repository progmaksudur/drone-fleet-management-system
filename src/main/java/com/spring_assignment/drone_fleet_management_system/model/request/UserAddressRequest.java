package com.spring_assignment.drone_fleet_management_system.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAddressRequest {

    @NotBlank(message = "Please provide your local area name")
    @NotNull(message = "Please provide your local area name")
    private String area;
    @NotBlank(message = "Please provide your local police station name")
    @NotNull(message = "Please provide your local police station name")
    private String policeStation;
    @NotBlank(message = "Please provide your local postal code ")
    @NotNull(message = "Please provide your local postal code")
    private String postalCode;
    @NotBlank(message = "Please provide your city name")
    @NotNull(message = "Please provide your city name")
    private String city;
    @NotBlank(message = "Please provide your country name")
    @NotNull(message = "Please provide your country name")
    private String country;
}
