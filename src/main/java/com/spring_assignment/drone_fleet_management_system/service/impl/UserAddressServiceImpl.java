package com.spring_assignment.drone_fleet_management_system.service.impl;

import com.spring_assignment.drone_fleet_management_system.entity.User;
import com.spring_assignment.drone_fleet_management_system.entity.UserAddress;
import com.spring_assignment.drone_fleet_management_system.exception.ResourceNotFoundException;
import com.spring_assignment.drone_fleet_management_system.model.request.UserAddressRequest;
import com.spring_assignment.drone_fleet_management_system.model.response.UserAddressDTO;
import com.spring_assignment.drone_fleet_management_system.repository.UserAddressRepository;
import com.spring_assignment.drone_fleet_management_system.service.UserAddressService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserAddressServiceImpl implements UserAddressService {

    private UserAddressRepository repository;
    private ModelMapper modelMapper;

    public UserAddressServiceImpl(UserAddressRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserAddressDTO createUserAddress(UserAddressRequest addressRequest, User user) {
        UserAddress userAddress=modelMapper.map(addressRequest, UserAddress.class);

        user.setAddress(userAddress);
        userAddress.setUser(user);

        return modelMapper.map(repository.save(userAddress),UserAddressDTO.class);
    }

    @Override
    public UserAddressDTO updateUserAddress(UserAddressRequest addressRequest, long id) {
        UserAddress userAddress = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Address not found"));
        modelMapper.getConfiguration().setPropertyCondition(context -> {
            Object sourceValue = context.getSource();
            return sourceValue != null && !(sourceValue instanceof String && ((String) sourceValue).isEmpty());
        });
        modelMapper.map(addressRequest,userAddress);
        return modelMapper.map(repository.save(userAddress),UserAddressDTO.class);
    }

    @Override
    public UserAddressDTO getUserAddressDetails(Long id) {
        UserAddress userAddress = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Address not found"));
        return modelMapper.map(userAddress,UserAddressDTO.class);
    }
}
