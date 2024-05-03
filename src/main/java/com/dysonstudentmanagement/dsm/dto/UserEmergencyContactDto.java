package com.dysonstudentmanagement.dsm.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
/*
UserEmergencyContactDto

Defines fields/data that will be transmitted to/from the API client when requesting/sending UserEmergencyContact data.

Original Author: Billy Peters 24/04/2024
 */
public class UserEmergencyContactDto {
    private String userID;
    private int contactPriority;
    private String firstName;
    private String lastName;
    private String title;
    private String email;
    private String phone;
    private String relation;
    private String address;
    private String postcode;
}
