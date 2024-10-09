package com.spring_assignment.drone_fleet_management_system.service.impl;


import com.spring_assignment.drone_fleet_management_system.entity.Drone;
import com.spring_assignment.drone_fleet_management_system.entity.FlightLog;
import com.spring_assignment.drone_fleet_management_system.exception.ResourceNotFoundException;
import com.spring_assignment.drone_fleet_management_system.model.request.FlightLogRequest;
import com.spring_assignment.drone_fleet_management_system.model.response.FlightLogDTO;
import com.spring_assignment.drone_fleet_management_system.repository.FlightLogRepository;
import com.spring_assignment.drone_fleet_management_system.service.FlightLogService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightLogServiceImpl implements FlightLogService {
    private ModelMapper modelMapper;
    private FlightLogRepository repository;

    public FlightLogServiceImpl(ModelMapper modelMapper, FlightLogRepository repository) {
        this.modelMapper = modelMapper;
        this.repository = repository;
    }

    @Override
    public FlightLogDTO createFlightLog(FlightLogRequest request,Drone drone) {

        FlightLog createdLog=modelMapper.map(request,FlightLog.class);
        createdLog.setDrone(drone);
        drone.setFlightLogList(List.of(createdLog));

        return modelMapper.map(repository.save(createdLog),FlightLogDTO.class);
    }

    @Override
    public FlightLogDTO getFlightLog(Long id) {
        FlightLog flightLog=repository.findById(id).orElseThrow(()->new ResourceNotFoundException("This drone id :"+id+" do not have flight logs"));
        return modelMapper.map(flightLog,FlightLogDTO.class);
    }

    @Override
    public List<FlightLogDTO> getSpecificFlightLogForDrone(Drone drone) {
       List<FlightLog> flightLogList = repository.findByDrone(drone);
        return flightLogList.stream().map((flightLog)->modelMapper.map(flightLog,FlightLogDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<FlightLogDTO> getAllFlightLog() {
        List<FlightLog> flightLogList = repository.findAll();
        return flightLogList.stream().map((flightLog)->modelMapper.map(flightLog,FlightLogDTO.class)).collect(Collectors.toList());
    }

    @Override
    public FlightLogDTO updateFlightLog(FlightLogRequest request,Long id) {
        FlightLog existingFlightLog=repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Flight log not found for this id ->"+id));
        modelMapper.getConfiguration().setPropertyCondition(context -> {
            Object sourceValue = context.getSource();
            return sourceValue != null && !(sourceValue instanceof String && ((String) sourceValue).isEmpty());
        });
        modelMapper.map(request,existingFlightLog);

        return modelMapper.map(repository.save(existingFlightLog),FlightLogDTO.class);
    }
}
