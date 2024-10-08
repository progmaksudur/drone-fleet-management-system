package com.spring_assignment.drone_fleet_management_system.repository;

import com.spring_assignment.drone_fleet_management_system.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmailOrPhone(String email, String phone);
    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String phone);
    Boolean existsByEmail(String email);
    Boolean existsByPhone(String phone);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.userVerifiedByEmail = true WHERE u.email = :emailOrPhone OR u.phone = :emailOrPhone")
    int verifyUserByEmail(String emailOrPhone);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.userVerifiedByPhone = true WHERE u.email = :emailOrPhone OR u.phone = :emailOrPhone")
    int verifyUserByPhone(String emailOrPhone);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.userActivated = true WHERE u.email = :emailOrPhone OR u.phone = :emailOrPhone")
    int activateUserByEmailOrPhone(String emailOrPhone);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.userActivated = false WHERE u.email = :emailOrPhone OR u.phone = :emailOrPhone")
    int deactivateUserByEmailOrPhone(String emailOrPhone);

}
