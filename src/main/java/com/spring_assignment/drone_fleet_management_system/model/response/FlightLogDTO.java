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
public class FlightLogDTO {

    private Long id;
    private long droneId;
    private String serialNumber;
    private String name;
    private String model;
    private double distance;
    private double maxAltitude;
    private double avgSpeed;
    private double maxSpeed;
    private double lowestSpeed;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
