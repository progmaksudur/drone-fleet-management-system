package com.spring_assignment.drone_fleet_management_system.service;

import com.spring_assignment.drone_fleet_management_system.entity.User;
import com.spring_assignment.drone_fleet_management_system.model.request.DroneCreateRequest;
import com.spring_assignment.drone_fleet_management_system.model.response.DroneDto;

import java.util.List;

public interface DroneService {

        public DroneDto createDroneInfo(DroneCreateRequest createRequest, User user);
        public DroneDto getSpecificDroneInfo(Long id);
        public List<DroneDto> getDronesForSpecificUser(User user);
        public List<DroneDto> getALlDrones();
        public void deleteSpecificDrone(Long id);


}
