package com.dysonstudentmanagement.dsm.dto;

import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
//@AllArgsConstructor
@ToString
/*
AuthResponseDto

Class contains the data transmitted in response to an authorisation request.

Original Author: Fang-Yu Kung 28/04/2024
 */
public class AuthResponseDto {
    private String accessToken;
    private String userID;
    private UserPrimaryData.UserType userType;
    private String firstName;
    private String lastName;
    private String dysonEmail;

    public AuthResponseDto(String accessToken, String userID,
                           UserPrimaryData.UserType userType,
                           String firstName,
                           String lastName,
                           String dysonEmail) {
        this.accessToken = accessToken;
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dysonEmail = dysonEmail;
        this.userType = userType;

    }

    public AuthResponseDto() {

    }
}
