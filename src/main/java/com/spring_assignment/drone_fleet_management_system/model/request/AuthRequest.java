package com.spring_assignment.drone_fleet_management_system.model.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    @NotBlank(message = "Please your email or phone !!")
    @NotNull(message = "Please your email or phone !!")
    private String emailOrPhone;
    @NotBlank(message = "Please your password !!")
    @NotNull(message = "Please your password !!")
    private String password;
}
