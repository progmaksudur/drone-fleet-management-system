package com.spring_assignment.drone_fleet_management_system.repository;

import com.spring_assignment.drone_fleet_management_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
