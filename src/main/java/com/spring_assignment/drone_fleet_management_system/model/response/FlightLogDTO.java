package com.spring_assignment.drone_fleet_management_system.model.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
