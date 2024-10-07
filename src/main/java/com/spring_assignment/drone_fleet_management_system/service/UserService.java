package com.spring_assignment.drone_fleet_management_system.service;

import com.spring_assignment.drone_fleet_management_system.model.request.UserSignUpRequest;
import com.spring_assignment.drone_fleet_management_system.model.response.UserDTO;

import java.util.List;

public interface UserService {


    public void createUser(UserSignUpRequest userSignUpRequest);

    public UserDTO getUserDetails(Long userID);

    public UserDTO getUserDetailsByEmailOrPhone(String emailOrPhone);

    public List<UserDTO> getAllUser();

    public void updateUser();

    public void deleteUser();

    public void verifyUserByEmail();

    public void verifyUserByPhone();

    public void deactivateUser();

    public void activateUser();







}
