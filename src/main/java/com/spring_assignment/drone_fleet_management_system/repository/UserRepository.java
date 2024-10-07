package com.spring_assignment.drone_fleet_management_system.repository;

import com.spring_assignment.drone_fleet_management_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmailOrPhone(String email, String phone);
    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String phone);
    Boolean existsByEmail(String email);
    Boolean existsByPhone(String phone);
}
