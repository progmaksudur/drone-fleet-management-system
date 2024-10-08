package com.spring_assignment.drone_fleet_management_system.model.request;

import com.spring_assignment.drone_fleet_management_system.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {

    private String title;

    private String firstName;

    private String lastName;

    private int gender; /// 1-->male,2-->female

}
