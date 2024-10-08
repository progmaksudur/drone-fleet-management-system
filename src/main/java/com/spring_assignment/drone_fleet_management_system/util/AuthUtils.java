package com.spring_assignment.drone_fleet_management_system.util;

import com.spring_assignment.drone_fleet_management_system.entity.User;
import com.spring_assignment.drone_fleet_management_system.security.JwtUtil;
import com.spring_assignment.drone_fleet_management_system.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class AuthUtils {

    private UserService userService;
    private JwtUtil jwtUtil;

    public AuthUtils(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    public User getUserByItsToken(String token) {
        String userName = jwtUtil.extractUsernameBearerToken(token);
        return userService.getUserDetails(userName);
    }

}
