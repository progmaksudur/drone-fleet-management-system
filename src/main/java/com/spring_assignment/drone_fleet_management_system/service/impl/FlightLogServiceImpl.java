package com.spring_assignment.drone_fleet_management_system.service.impl;


import com.spring_assignment.drone_fleet_management_system.entity.Drone;
import com.spring_assignment.drone_fleet_management_system.entity.FlightLog;
import com.spring_assignment.drone_fleet_management_system.exception.ResourceNotFoundException;
import com.spring_assignment.drone_fleet_management_system.model.request.FlightLogRequest;
import com.spring_assignment.drone_fleet_management_system.model.response.FlightLogDTO;
import com.spring_assignment.drone_fleet_management_system.repository.FlightLogRepository;
import com.spring_assignment.drone_fleet_management_system.service.FlightLogService;
import com.spring_assignment.drone_fleet_management_system.util.FlighLogUtils;
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

        FlightLog createdLog =  FlighLogUtils.convartFlightLog(request,drone);
        System.out.println(createdLog.toString());
        FlightLog flightLog=repository.save(createdLog);

        return FlighLogUtils.convartFlightLogDto(drone,flightLog);
    }

    @Override
    public FlightLogDTO getFlightLog(Long id) {
        FlightLog log=repository.findById(id).orElseThrow(()->new ResourceNotFoundException("This drone id :"+id+" do not have flight logs"));
        return  FlighLogUtils.convartFlightLogDto(log.getDrone(),log);
    }

    @Override
    public List<FlightLogDTO> getSpecificFlightLogForDrone(Drone drone) {
       List<FlightLog> flightLogList = repository.findByDrone(drone);
        return flightLogList.stream().map((flightLog)->FlighLogUtils.convartFlightLogDto(drone,flightLog)).collect(Collectors.toList());
    }

    @Override
    public List<FlightLogDTO> getAllFlightLog() {
        List<FlightLog> flightLogs=repository.findAll();
        return flightLogs.stream().map((logs)->FlighLogUtils.convartFlightLogDto(logs.getDrone(),logs)).collect(Collectors.toList());
    }

    @Override
    public FlightLogDTO updateFlightLog(FlightLogRequest request,Long id) {
        FlightLog existingFlightLog=repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Flight log not found for this id ->"+id));


        ///Custom mapping for this

        FlightLog updateLog= repository.save(FlighLogUtils.getUpdateFlightLog(request,existingFlightLog));

        return FlighLogUtils.convartFlightLogDto(updateLog.getDrone(),updateLog);
//        return FlighLogUtils.getUpdateFlightLog(request);
    }
}
