package com.spring_assignment.drone_fleet_management_system.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private LocalDateTime timestamp;
    private int status;
    private String jwtToken;
}
