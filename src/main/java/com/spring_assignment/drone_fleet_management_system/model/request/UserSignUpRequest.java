package com.spring_assignment.drone_fleet_management_system.model.request;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpRequest {

    @NotBlank(message = "Please provide a title")
    @NotNull(message = "Please provide a title")
    private String title;
    @NotBlank(message = "Please provide your First name")
    @NotNull(message = "Please provide your First name")
    private String firstName;
    @NotBlank(message = "Please provide your Last name")
    @NotNull(message = "Please provide your Last name")
    private String lastName;
    @NotBlank(message = "Please provide valid email")
    @NotNull(message = "Please provide valid email")
    @Email(message = "Please provide valid email")
    private String email;
    @NotBlank(message = "Please provide valid phone")
    @NotNull(message = "Please provide valid phone")
    private String phone;
    @NotBlank(message = "Please provide password")
    @NotNull(message = "Please provide password")
    private String password;

}
