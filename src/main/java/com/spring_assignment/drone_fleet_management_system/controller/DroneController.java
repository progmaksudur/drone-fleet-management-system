package com.spring_assignment.drone_fleet_management_system.controller;

import com.spring_assignment.drone_fleet_management_system.entity.Drone;
import com.spring_assignment.drone_fleet_management_system.entity.User;
import com.spring_assignment.drone_fleet_management_system.model.request.DroneCreateRequest;
import com.spring_assignment.drone_fleet_management_system.model.request.FlightLogRequest;
import com.spring_assignment.drone_fleet_management_system.model.response.DroneDto;
import com.spring_assignment.drone_fleet_management_system.service.DroneService;
import com.spring_assignment.drone_fleet_management_system.service.FlightLogService;
import com.spring_assignment.drone_fleet_management_system.util.AuthUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DroneController {
    private DroneService droneService;
    private AuthUtils authUtils;
    private FlightLogService flightLogService;


    public DroneController(DroneService droneService, AuthUtils authUtils, FlightLogService flightLogService) {
        this.droneService = droneService;
        this.authUtils = authUtils;
        this.flightLogService = flightLogService;
    }

    @PostMapping("/drone/create")
    public ResponseEntity<DroneDto> createDrone(@RequestBody DroneCreateRequest createRequest, @RequestHeader(name="Authorization") String token){

        User user=authUtils.getUserByItsToken(token);


        return new ResponseEntity<>(droneService.createDroneInfo(createRequest,user), HttpStatus.OK);

    }

    @GetMapping("/drone/getAll")
    public ResponseEntity<List<DroneDto>> getAllDrone(){

        return new ResponseEntity<>(droneService.getALlDrones(), HttpStatus.OK);

    }

    @GetMapping("/drone/getUserDrones")
    public ResponseEntity<List<DroneDto>> getDronesByUser(@RequestHeader(name="Authorization") String token){
        User user=authUtils.getUserByItsToken(token);
        return new ResponseEntity<>(droneService.getDronesForSpecificUser(user), HttpStatus.OK);
    }

    @DeleteMapping("/drone/delete-info")
    public ResponseEntity<String> deleteDroneInfo(@RequestBody Long id){

        droneService.deleteSpecificDrone(id);
        return new ResponseEntity<>("Delete successfully", HttpStatus.OK);
    }

    @GetMapping("/drone/getSpecificDrone")
    public ResponseEntity<?> getSpecificDroneInfo(@RequestParam Long id){

        return new ResponseEntity<>(droneService.getSpecificDroneInfo(id), HttpStatus.OK);

    }

    @PostMapping("/flight/create")
    public ResponseEntity<?> createFlightLog(@RequestBody FlightLogRequest request){
        Drone drone=droneService.getDrone(request.getDroneId());

        return new ResponseEntity<>(flightLogService.createFlightLog(request,drone), HttpStatus.OK);
    }

    @PutMapping("/flight/update")
    public ResponseEntity<?> updateFlightLog(@RequestBody FlightLogRequest request,@RequestParam long id){

        return new ResponseEntity<>(flightLogService.updateFlightLog(request,id), HttpStatus.OK);
    }

    @GetMapping("/flight/getFlightLog")
    public ResponseEntity<?> getFlightLog(@RequestParam Long id){

        return new ResponseEntity<>(flightLogService.getFlightLog(id), HttpStatus.OK);
    }
    @GetMapping("/flight/getAllFlightLog")
    public ResponseEntity<?> getAllFlightLog(){

        return new ResponseEntity<>(flightLogService.getAllFlightLog(), HttpStatus.OK);
    }
    @GetMapping("/flight/getSpecificDroneAllFlightLog")
    public ResponseEntity<?> getAllFlightLogForSpecificDrone(@RequestParam long id){
        Drone drone=droneService.getDrone(id);

        return new ResponseEntity<>(flightLogService.getSpecificFlightLogForDrone(drone), HttpStatus.OK);
    }



}
