package com.spring_assignment.drone_fleet_management_system.entity;

import com.spring_assignment.drone_fleet_management_system.enums.DroneStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "drone_tb")
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private String serialNumber;
    private String name;
    private String model;
    private double batteryPercentage;

    @Enumerated(EnumType.STRING)
    private DroneStatus status; // e.g., active, inactive, in-flight, charging
    private double latitude;
    private double longitude;
    private double altitude;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @OneToMany(mappedBy = "droneId",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<FlightLog> flightLogList;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
