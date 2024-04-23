package com.dysonstudentmanagement.dsm.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserEmergencyContactDto {
    private String userID;
    private String firstName;
    private String lastName;
    private String title;
    private String email;
    private String phone;
    private String relation;
    private String address;
    private String postcode;
}
