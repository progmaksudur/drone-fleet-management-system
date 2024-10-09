package com.spring_assignment.drone_fleet_management_system.repository;

import com.spring_assignment.drone_fleet_management_system.entity.Drone;
import com.spring_assignment.drone_fleet_management_system.entity.FlightLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightLogRepository extends JpaRepository<FlightLog,Long> {

    public List<FlightLog> findByDrone(Drone drone);
}
