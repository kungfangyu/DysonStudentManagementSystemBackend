package com.dysonstudentmanagement.dsm.dto.user;

import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import lombok.*;


import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class UserPrimaryDataDto {
    private String userID;
    private UserPrimaryData.UserType userType;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String personalEmail;
    private String dysonEmail;
    private String phone;
}
