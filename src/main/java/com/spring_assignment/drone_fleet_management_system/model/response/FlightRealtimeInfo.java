package com.spring_assignment.drone_fleet_management_system.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightRealtimeInfo {

    private Long id;
    private String droneName;
    private String currentPosiion;
    private double currentSpeed;


}
