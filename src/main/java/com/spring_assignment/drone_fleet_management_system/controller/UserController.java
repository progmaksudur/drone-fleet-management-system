package com.spring_assignment.drone_fleet_management_system.controller;


import com.spring_assignment.drone_fleet_management_system.model.request.AuthRequest;
import com.spring_assignment.drone_fleet_management_system.model.request.UserSignUpRequest;
import com.spring_assignment.drone_fleet_management_system.model.response.AuthResponse;
import com.spring_assignment.drone_fleet_management_system.security.JwtUtil;
import com.spring_assignment.drone_fleet_management_system.service.UserService;
import com.spring_assignment.drone_fleet_management_system.service.impl.UserDetailsServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class UserController {

    private UserService userService;
    private JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;
    private UserDetailsServiceImpl userDetailsService;

    public UserController(UserService userService, JwtUtil jwtUtil, AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<?> signupUser(@RequestBody UserSignUpRequest request){
        String message = userService.createUser(request);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmailOrPhone(), request.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmailOrPhone());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(new AuthResponse(LocalDateTime.now(),HttpStatus.OK.value(),jwt), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.toString());
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }
    }

}
