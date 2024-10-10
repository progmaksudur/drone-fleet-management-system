package com.spring_assignment.drone_fleet_management_system.util;

import com.spring_assignment.drone_fleet_management_system.entity.Drone;
import com.spring_assignment.drone_fleet_management_system.entity.FlightLog;
import com.spring_assignment.drone_fleet_management_system.model.request.FlightLogRequest;
import com.spring_assignment.drone_fleet_management_system.model.response.FlightLogDTO;

import java.time.LocalDateTime;


public class FlighLogUtils {

     public static FlightLogDTO convartFlightLogDto(Drone drone, FlightLog flightLog){

        return FlightLogDTO.builder()
                .id(flightLog.getId())
                .serialNumber(drone.getSerialNumber())
                .droneId(drone.getId())
                .name(drone.getName())
                .model(drone.getModel())
                .distance(flightLog.getDistance())
                .maxAltitude(flightLog.getMaxAltitude())
                .avgSpeed(flightLog.getAvgSpeed())
                .maxSpeed(flightLog.getMaxSpeed())
                .lowestSpeed(flightLog.getLowestSpeed())
                .startTime(flightLog.getStartTime())
                .endTime(flightLog.getEndTime())
                .build();
    }
     public static FlightLog convartFlightLog(FlightLogRequest request, Drone drone){

        return FlightLog.builder()
                .drone(drone)
                .startTime(LocalDateTime.parse(request.getStartTime()))
                .endTime(LocalDateTime.parse(request.getEndTime()))
                .distance(request.getDistance())
                .maxAltitude(request.getMaxAltitude())
                .maxSpeed(request.getMaxSpeed())
                .avgSpeed(request.getAvgSpeed())
                .lowestSpeed(request.getLowestSpeed())
                .build();

    }

     public static FlightLog getUpdateFlightLog(FlightLogRequest request,FlightLog existingLog) {


         existingLog.setDistance(request.getDistance()==null? existingLog.getDistance() : request.getDistance());
         existingLog.setMaxAltitude(request.getMaxAltitude()==null? existingLog.getMaxAltitude() : request.getMaxAltitude());
         existingLog.setMaxSpeed(request.getMaxSpeed()==null?existingLog.getMaxSpeed(): request.getMaxSpeed());
         existingLog.setAvgSpeed(request.getAvgSpeed()==null?existingLog.getAvgSpeed(): request.getAvgSpeed());
         existingLog.setLowestSpeed(request.getLowestSpeed()==null?existingLog.getLowestSpeed(): request.getLowestSpeed());



        if (request.getStartTime() != null && !request.getStartTime().isEmpty()) {
            LocalDateTime startTime = LocalDateTime.parse(request.getStartTime());
            existingLog.setStartTime(startTime);
        }
        if (request.getEndTime() != null && !request.getEndTime().isEmpty()) {
            LocalDateTime endTime = LocalDateTime.parse(request.getEndTime());
            existingLog.setEndTime(endTime);
        }
        return existingLog;
    }

}
/*
* private Long id;
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
*
* */