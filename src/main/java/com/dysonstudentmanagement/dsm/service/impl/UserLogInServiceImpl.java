package com.dysonstudentmanagement.dsm.service.impl;

import com.dysonstudentmanagement.dsm.dto.UserLogInDto;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.entity.userlogin.UserLogIn;
import com.dysonstudentmanagement.dsm.exception.InvalidUserTypeException;
import com.dysonstudentmanagement.dsm.exception.ResourceNotFoundException;
import com.dysonstudentmanagement.dsm.mapper.UserLogInMapper;
import com.dysonstudentmanagement.dsm.repository.UserLogInRepository;
import com.dysonstudentmanagement.dsm.repository.UserPrimaryDataRepository;
import com.dysonstudentmanagement.dsm.service.UserLogInService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserLogInServiceImpl implements UserLogInService {

    private UserLogInRepository userLogInRepository;

    private UserPrimaryDataRepository userPrimaryDataRepo;

    @Override
    public String createUserLogIn(UserLogInDto userLogInDto) {
        UserLogIn userLogIn = UserLogInMapper.mapToUserLogIn(userLogInDto);
        UserPrimaryData userData = userPrimaryDataRepo.findById(userLogIn.getUserID())
                .orElseThrow(() -> new DataIntegrityViolationException("Failed...UserID does not exist in foreign key table 'UserPrimaryData'"));

        userLogInRepository.save(userLogIn);
        return userLogIn.getUserID();
    }

    @Override
    public UserLogIn getUserByUserIDAndPassword(String userID, String password) throws InvalidUserTypeException {
        UserLogIn userLogin = userLogInRepository.findByUserIDAndPassword(userID, password);
        return userLogin;
    }

    @Override
    public String updateUserPassword(UserLogInDto userLogInDto) {
        UserLogIn userLogin = UserLogInMapper.mapToUserLogIn(userLogInDto);

        UserLogIn userExists = userLogInRepository.findById(userLogin.getUserID())
                .orElseThrow(()-> new ResourceNotFoundException("UserId not found in UserLogin table"));
        userExists.setPassword(userLogin.getPassword());
        userLogInRepository.save(userExists);
        return "Password Updated";

    }

}
