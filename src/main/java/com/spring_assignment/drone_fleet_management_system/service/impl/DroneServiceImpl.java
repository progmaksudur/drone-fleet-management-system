package com.spring_assignment.drone_fleet_management_system.service.impl;


import com.spring_assignment.drone_fleet_management_system.entity.Drone;
import com.spring_assignment.drone_fleet_management_system.entity.User;
import com.spring_assignment.drone_fleet_management_system.exception.ResourceNotFoundException;
import com.spring_assignment.drone_fleet_management_system.model.request.DroneCreateRequest;
import com.spring_assignment.drone_fleet_management_system.model.response.DroneDto;
import com.spring_assignment.drone_fleet_management_system.repository.DroneRepository;
import com.spring_assignment.drone_fleet_management_system.service.DroneService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        System.out.println(createRequest.getSerialNumber());
        Drone drone = modelMapper.map(createRequest,Drone.class);
        drone.setUser(user);
        user.setDroneList(List.of(drone));

        return modelMapper.map(repository.save(drone),DroneDto.class);
    }

    @Override
    public DroneDto getSpecificDroneInfo(Long id) {

        Drone drone=repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Drone id "+id+"not found"));

        return modelMapper.map(drone,DroneDto.class);
    }

    @Override
    public Drone getDrone(Long id) {
        return repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Drone id "+id+"not found"));
    }

    @Override
    public List<DroneDto> getDronesForSpecificUser(User user) {
        List<Drone> droneList=repository.findByUser(user);
        return droneList.stream().map((drone)->modelMapper.map(drone,DroneDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<DroneDto> getALlDrones() {
        List<Drone> droneList=repository.findAll();
        return  droneList.stream().map((drone)->modelMapper.map(drone,DroneDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteSpecificDrone(Long id) {
        Drone drone=repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Drone id "+id+"not found"));
        repository.delete(drone);
    }
}
