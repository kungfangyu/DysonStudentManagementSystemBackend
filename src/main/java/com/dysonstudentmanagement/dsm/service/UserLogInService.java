package com.dysonstudentmanagement.dsm.service;

import com.dysonstudentmanagement.dsm.dto.UserLogInDto;
import com.dysonstudentmanagement.dsm.entity.userlogin.UserLogIn;
import com.dysonstudentmanagement.dsm.repository.UserLogInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
UserLogInService

Interface that declares service methods that act upon UserLogIn table in database

Original Author: Fang-Yu Kung 28/04/2024
Modifying Author: Billy Peters 30/04/2024: Added createUserLogin and updateUserPassword methods
 */
public interface UserLogInService{
    String createUserLogIn(UserLogInDto userLoginDto);

    UserLogIn getUserByUserIDAndPassword(String userID, String password);

    String updateUserPassword(UserLogInDto userLogInDto);
}
