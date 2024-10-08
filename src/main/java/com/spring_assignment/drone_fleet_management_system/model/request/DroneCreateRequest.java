package com.spring_assignment.drone_fleet_management_system.model.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DroneCreateRequest {
    private Long id;
    private String serialNumber;
    private String name;
    private String model;
    private double batteryPercentage;
    private double latitude;
    private double longitude;
    private double altitude;
}
