package com.spring_assignment.drone_fleet_management_system.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DroneDto {

    private Long id;
    private String serialNumber;
    private String name;
    private String model;
    private double batteryPercentage;
    private double latitude;
    private double longitude;
    private double altitude;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
