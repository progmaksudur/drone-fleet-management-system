package com.spring_assignment.drone_fleet_management_system.service;

import com.spring_assignment.drone_fleet_management_system.model.request.UserSignUpRequest;
import com.spring_assignment.drone_fleet_management_system.model.request.UserUpdateRequest;
import com.spring_assignment.drone_fleet_management_system.model.response.UserDTO;

import java.util.List;

public interface UserService {


    public String createUser(UserSignUpRequest userSignUpRequest);

    public UserDTO getUserDetails(Long userID);

    public UserDTO getUserDetailsByEmailOrPhone(String emailOrPhone);

    public List<UserDTO> getAllUser();

    public void updateUser(UserUpdateRequest userUpdateRequest,String emailOrPhone);

    public UserDTO resetPassword(String password,String emailOrPhone);

    public void deleteUser(String emailOrPhone);

    public int verifyUserByEmail(String emailOrPhone);

    public int verifyUserByPhone(String emailOrPhone);

    public int deactivateUser(String emailOrPhone);

    public int activateUser(String emailOrPhone);







}
