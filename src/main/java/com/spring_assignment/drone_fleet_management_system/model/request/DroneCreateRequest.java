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
    private Double batteryPercentage;
    private Double latitude;
    private Double longitude;
    private Double altitude;
}
