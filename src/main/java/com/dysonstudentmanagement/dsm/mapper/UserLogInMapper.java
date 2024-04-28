package com.dysonstudentmanagement.dsm.mapper;

import com.dysonstudentmanagement.dsm.dto.UserLogInDto;
import com.dysonstudentmanagement.dsm.entity.userlogin.UserLogIn;
import org.springframework.stereotype.Component;

@Component
public class UserLogInMapper {

    public static UserLogIn mapToUserLogIn(UserLogInDto userLoginDto) {
        UserLogIn userLogin = new UserLogIn();
        userLogin.setUserID(userLoginDto.getUserID());
        userLogin.setPassword(userLoginDto.getPassword());
        return userLogin;
    }

}
