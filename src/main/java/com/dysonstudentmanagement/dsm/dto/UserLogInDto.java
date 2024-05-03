package com.dysonstudentmanagement.dsm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Data
/*
UserLogInDto

Defines fields/data that will be transmitted from the API client when sending logIn data.

Original Author: Fang-Yu Kung 28/04/2024
 */
public class UserLogInDto {
    private String userID;
    private String password;
}
