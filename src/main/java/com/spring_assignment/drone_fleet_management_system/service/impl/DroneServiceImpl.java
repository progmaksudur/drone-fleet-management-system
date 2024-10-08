package com.spring_assignment.drone_fleet_management_system.service.impl;


import com.spring_assignment.drone_fleet_management_system.entity.User;
import com.spring_assignment.drone_fleet_management_system.model.request.DroneCreateRequest;
import com.spring_assignment.drone_fleet_management_system.model.response.DroneDto;
import com.spring_assignment.drone_fleet_management_system.repository.DroneRepository;
import com.spring_assignment.drone_fleet_management_system.service.DroneService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DroneServiceImpl implements DroneService {
    private DroneRepository repository;
    private ModelMapper modelMapper;

    public DroneServiceImpl(DroneRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DroneDto createDroneInfo(DroneCreateRequest createRequest, User user) {
        return null;
    }

    @Override
    public DroneDto getSpecificDroneInfo(Long id) {
        return null;
    }

    @Override
    public List<DroneDto> getDronesForSpecificUser(User user) {
        return List.of();
    }

    @Override
    public List<DroneDto> getALlDrones() {
        return List.of();
    }

    @Override
    public void deleteSpecificDrone(Long id) {

    }
}
