package com.dysonstudentmanagement.dsm.service.impl;

import com.dysonstudentmanagement.dsm.dto.UserLogInDto;
import com.dysonstudentmanagement.dsm.entity.userlogin.UserLogIn;
import com.dysonstudentmanagement.dsm.exception.InvalidUserTypeException;
import com.dysonstudentmanagement.dsm.mapper.UserLogInMapper;
import com.dysonstudentmanagement.dsm.repository.UserLogInRepository;
import com.dysonstudentmanagement.dsm.service.UserLogInService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class UserLogInServiceImpl implements UserLogInService {

    private final UserLogInRepository userLogInRepository;

    @Autowired
    public UserLogInServiceImpl(UserLogInRepository userLogInRepository) {
        this.userLogInRepository = userLogInRepository;
    }

    @Override
    public UserLogIn createUserLogIn(UserLogInDto userLogInDto) {
        UserLogIn loginDetails = UserLogInMapper.mapToUserLogIn(userLogInDto);
        return userLogInRepository.save(loginDetails);
    }

    @Override
    public UserLogIn getUserByUserIDAndPassword(String userID, String password) throws InvalidUserTypeException {
        UserLogIn userLogin = userLogInRepository.findByUserIDAndPassword(userID, password);
        return userLogin;
    }

}
