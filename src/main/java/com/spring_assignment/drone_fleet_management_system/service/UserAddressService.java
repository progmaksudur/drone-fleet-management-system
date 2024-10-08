package com.spring_assignment.drone_fleet_management_system.service;

import com.spring_assignment.drone_fleet_management_system.entity.User;
import com.spring_assignment.drone_fleet_management_system.model.request.UserAddressRequest;
import com.spring_assignment.drone_fleet_management_system.model.response.UserAddressDTO;


public interface UserAddressService {


    public UserAddressDTO createUserAddress(UserAddressRequest addressRequest, User user);

    public UserAddressDTO updateUserAddress(UserAddressRequest addressRequest,long id);

    public UserAddressDTO getUserAddressDetails(Long id);

}
