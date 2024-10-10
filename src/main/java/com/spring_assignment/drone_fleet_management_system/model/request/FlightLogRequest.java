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
    private Long droneId;
    private Double distance;
    private Double maxAltitude;
    private Double avgSpeed;
    private Double maxSpeed;
    private Double lowestSpeed;
    private String startTime;
    private String endTime;
}
