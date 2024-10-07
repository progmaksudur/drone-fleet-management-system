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
public class UserAddressCreateRequest {
    @NotBlank(message = "Please provide your area ")
    @NotNull(message = "Please provide your area ")
    private String area;
    @NotBlank(message = "Please provide your police station ")
    @NotNull(message = "Please provide your police station ")
    private String policeStation;
    @NotBlank(message = "Please provide your postal code ")
    @NotNull(message = "Please provide your postal code ")
    private String postalCode;
    @NotBlank(message = "Please provide your city ")
    @NotNull(message = "Please provide your city ")
    private String city;
    @NotBlank(message = "Please provide your country ")
    @NotNull(message = "Please provide your country ")
    private String country;

}
