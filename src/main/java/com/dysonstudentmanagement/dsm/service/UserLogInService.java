package com.dysonstudentmanagement.dsm.service;

import com.dysonstudentmanagement.dsm.dto.UserLogInDto;
import com.dysonstudentmanagement.dsm.entity.userlogin.UserLogIn;
import com.dysonstudentmanagement.dsm.repository.UserLogInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface UserLogInService{
    UserLogIn createUserLogIn(UserLogInDto userLoginDto);

    UserLogIn getUserByUserIDAndPassword(String userID, String password);


}
