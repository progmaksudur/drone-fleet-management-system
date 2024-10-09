package com.spring_assignment.drone_fleet_management_system.repository;

import com.spring_assignment.drone_fleet_management_system.entity.Drone;
import com.spring_assignment.drone_fleet_management_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends JpaRepository<Drone,Long> {

    public List<Drone> findByUser(User user);

}
