package com.spring_assignment.drone_fleet_management_system.service.impl;

import com.spring_assignment.drone_fleet_management_system.entity.User;
import com.spring_assignment.drone_fleet_management_system.enums.Roles;
import com.spring_assignment.drone_fleet_management_system.model.request.UserSignUpRequest;
import com.spring_assignment.drone_fleet_management_system.model.request.UserUpdateRequest;
import com.spring_assignment.drone_fleet_management_system.model.response.UserDTO;
import com.spring_assignment.drone_fleet_management_system.repository.UserRepository;
import com.spring_assignment.drone_fleet_management_system.service.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String createUser(UserSignUpRequest userSignUpRequest) {
        if(repository.existsByPhone(userSignUpRequest.getPhone())){
            System.out.println("phone found");
            return "This phone already have account";
        }else if(repository.existsByEmail(userSignUpRequest.getEmail())){
            System.out.println("email found");
            return "This email already have account";
        }else{
            userSignUpRequest.setPassword(passwordEncoder.encode(userSignUpRequest.getPassword()));
            User user = modelMapper.map(userSignUpRequest,User.class);
            user.setRoles(List.of(Roles.USER));
            user.setUserVerifiedByEmail(false);
            user.setUserVerifiedByPhone(false);
            repository.save(user);
            return "User Create Successfully";
        }

    }

    @Override
    public UserDTO getUserDetails(Long userID) {

      User user=  repository.findById(userID).orElseThrow(()-> new UsernameNotFoundException("User id -> "+userID+" not found"));

        return modelMapper.map(user,UserDTO.class);
    }

    @Override
    public UserDTO getUserDetailsByEmailOrPhone(String emailOrPhone) {

        User user=  (emailOrPhone.contains("@") ?
                repository.findByEmail(emailOrPhone):
                repository.findByPhone(emailOrPhone))
                .orElseThrow(()-> new UsernameNotFoundException("User  -> "+emailOrPhone+" not found"));

        return modelMapper.map(user,UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUser() {
        List<User> users =  repository.findAll();


        return users.stream().map((user)->modelMapper.map(user,UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void updateUser(UserUpdateRequest updateRequest,String emailOrPhone) {

        modelMapper.getConfiguration().setPropertyCondition(context -> {
            Object sourceValue = context.getSource();
            return sourceValue != null && !(sourceValue instanceof String && ((String) sourceValue).isEmpty());
        });
        User exestingUser=  (emailOrPhone.contains("@") ?
                repository.findByEmail(emailOrPhone):
                repository.findByPhone(emailOrPhone))
                .orElseThrow(()-> new UsernameNotFoundException("User  -> "+emailOrPhone+" not found"));


        modelMapper.map(updateRequest,exestingUser);

        try {
            repository.save(exestingUser);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDTO resetPassword(String password,String emailOrPhone) {
        User exestingUser=  (emailOrPhone.contains("@") ?
                repository.findByEmail(emailOrPhone):
                repository.findByPhone(emailOrPhone))
                .orElseThrow(()-> new UsernameNotFoundException("User  -> "+emailOrPhone+" not found"));

        exestingUser.setPassword(passwordEncoder.encode(password));
        return modelMapper.map(repository.save(exestingUser),UserDTO.class);
    }

    @Override
    public void deleteUser(String emailOrPhone) {
        User exestingUser=  (emailOrPhone.contains("@") ?
                repository.findByEmail(emailOrPhone):
                repository.findByPhone(emailOrPhone))
                .orElseThrow(()-> new UsernameNotFoundException("User  -> "+emailOrPhone+" not found"));

        repository.delete(exestingUser);
    }

    @Override
    public int verifyUserByEmail(String emailOrPhone) {

        return  repository.verifyUserByEmail(emailOrPhone);
    }

    @Override
    public int verifyUserByPhone(String emailOrPhone) {

        return  repository.verifyUserByEmail(emailOrPhone);
    }

    @Override
    public int deactivateUser(String emailOrPhone) {

        return repository.deactivateUserByEmailOrPhone(emailOrPhone);
    }

    @Override
    public int activateUser(String emailOrPhone) {

        return repository.activateUserByEmailOrPhone(emailOrPhone);
    }
}
