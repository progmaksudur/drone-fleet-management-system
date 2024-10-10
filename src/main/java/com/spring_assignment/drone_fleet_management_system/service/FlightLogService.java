package com.spring_assignment.drone_fleet_management_system.service;

import com.spring_assignment.drone_fleet_management_system.entity.Drone;

import com.spring_assignment.drone_fleet_management_system.model.request.FlightLogRequest;
import com.spring_assignment.drone_fleet_management_system.model.response.FlightLogDTO;

import java.util.List;

public interface FlightLogService {

    public FlightLogDTO createFlightLog(FlightLogRequest request,Drone drone);

    public FlightLogDTO getFlightLog(Long id);

    public List<FlightLogDTO> getSpecificFlightLogForDrone(Drone drone);

    public List<FlightLogDTO> getAllFlightLog();

    public FlightLogDTO updateFlightLog(FlightLogRequest request, Long id);


}
