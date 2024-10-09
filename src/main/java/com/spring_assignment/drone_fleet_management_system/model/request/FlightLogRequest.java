package com.spring_assignment.drone_fleet_management_system.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightLogRequest {
    private long droneId;
    private double distance;
    private double maxAltitude;
    private double avgSpeed;
    private double maxSpeed;
    private double lowestSpeed;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
