package com.spring_assignment.drone_fleet_management_system.controller;


import com.spring_assignment.drone_fleet_management_system.entity.User;
import com.spring_assignment.drone_fleet_management_system.model.request.*;
import com.spring_assignment.drone_fleet_management_system.model.response.AuthResponse;
import com.spring_assignment.drone_fleet_management_system.model.response.UserAddressDTO;
import com.spring_assignment.drone_fleet_management_system.model.response.UserDTO;
import com.spring_assignment.drone_fleet_management_system.security.JwtUtil;
import com.spring_assignment.drone_fleet_management_system.service.UserAddressService;
import com.spring_assignment.drone_fleet_management_system.service.UserService;
import com.spring_assignment.drone_fleet_management_system.service.impl.UserDetailsServiceImpl;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private UserService userService;
    private JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;
    private UserDetailsServiceImpl userDetailsService;
    private UserAddressService userAddressService;

    public UserController(UserService userService, JwtUtil jwtUtil, AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService, UserAddressService userAddressService) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.userAddressService = userAddressService;
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<?> signupUser(@Validated @RequestBody UserSignUpRequest request){
        String message = userService.createUser(request);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@Validated @RequestBody AuthRequest request){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmailOrPhone(), request.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmailOrPhone());
            System.out.println(userDetails.getAuthorities());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(new AuthResponse(LocalDateTime.now(),HttpStatus.OK.value(),jwt,"Log in successfully"), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.toString());
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/user/update")
    public ResponseEntity<?> updateUser(@RequestBody UserUpdateRequest request,@RequestHeader(name="Authorization") String token){
        String userName= jwtUtil.extractUsernameBearerToken(token);
        userService.updateUser(request,userName);
        return new ResponseEntity<>("Update Successfully", HttpStatus.OK);
    }

    @GetMapping("/user/details")
    public ResponseEntity<UserDTO> getUserDetails(@RequestHeader(name="Authorization") String token){

        String userName= jwtUtil.extractUsernameBearerToken(token);

        return new ResponseEntity<>(userService.getUserDetailsByEmailOrPhone(userName),HttpStatus.OK);
    }

    @PostMapping("/user/restPassword")
    public ResponseEntity<?> resetPassword(@RequestHeader(name="Authorization") String token , @RequestBody String password){
        String userName= jwtUtil.extractUsernameBearerToken(token);
        return new ResponseEntity<>(userService.resetPassword(password,userName),HttpStatus.OK);
    }

    @PostMapping("/auth/forgotPassword")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest request){
        UserDTO userDTO =userService.resetPassword(request.getNewPassword(),request.getUserName());

        if(userDTO!=null){
            return new ResponseEntity<>("Password Rest Successfully",HttpStatus.OK);
        }
        return  new ResponseEntity<>("Some thing went wrong",HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/user/updateAddress")
    public ResponseEntity<?> updateUserAddress(@RequestBody UserAddressRequest request,@RequestHeader(name="Authorization") String token){
        String userName= jwtUtil.extractUsernameBearerToken(token);
        User user= userService.getUserDetails(userName);
        if(user.getAddress()==null){
          UserAddressDTO userAddressDTO= userAddressService.createUserAddress(request,user);
          if(userAddressDTO!=null){
              return new ResponseEntity<>("Address created for user",HttpStatus.OK);
          }else{
              return  new ResponseEntity<>("Some thing went wrong",HttpStatus.BAD_REQUEST);
          }
        }else{
            UserAddressDTO userAddressDTO= userAddressService.updateUserAddress(request,user.getAddress().getId());
            if(userAddressDTO!=null){
                return new ResponseEntity<>("Address updated for user",HttpStatus.OK);
            }else{
                return  new ResponseEntity<>("Some thing went wrong",HttpStatus.BAD_REQUEST);
            }
        }
    }




}
