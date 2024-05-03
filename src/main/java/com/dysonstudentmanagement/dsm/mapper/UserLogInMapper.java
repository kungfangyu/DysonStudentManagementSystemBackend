package com.dysonstudentmanagement.dsm.mapper;

import com.dysonstudentmanagement.dsm.dto.UserLogInDto;
import com.dysonstudentmanagement.dsm.entity.userlogin.UserLogIn;
import org.springframework.stereotype.Component;

/*
    Class: UserEmergencyContactMapper
    Mapper class responsible for mapping from UserEmergencyContactDto to UserEmergencyContact objects.
    Original Author: Fang-Yu Kung 28/04/2024
 */
public class UserLogInMapper {

    public static UserLogIn mapToUserLogIn(UserLogInDto userLoginDto) {
        UserLogIn userLogin = new UserLogIn();
        userLogin.setUserID(userLoginDto.getUserID());
        userLogin.setPassword(userLoginDto.getPassword());
        return userLogin;
    }

}
