package com.spring_assignment.drone_fleet_management_system.exception;

public class BadRequestException extends ResourceNotFoundException{
    public BadRequestException(String message) {
        super(message);
    }
}
