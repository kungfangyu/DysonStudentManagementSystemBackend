package com.dysonstudentmanagement.dsm.dto;

import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import lombok.*;


import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@ToString
/*
UserPrimaryDataDto

Defines fields/data that will be transmitted to/from the API client when requesting/sending UserPrimaryData data.

Original Author: Billy Peters 23/04/2024
 */
public class UserPrimaryDataDto {
    private String userID;
    private UserPrimaryData.UserType userType;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String personalEmail;
    private String dysonEmail;
    private String phone;
    private String idPhoto;
    private String title;
    private String middleNames;
    private String gender;
    private String ethnicity;
    private String address;
    private String postcode;
}
