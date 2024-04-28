package com.dysonstudentmanagement.dsm.controller;

import com.dysonstudentmanagement.dsm.dto.AuthResponseDto;
import com.dysonstudentmanagement.dsm.dto.UserLogInDto;
import com.dysonstudentmanagement.dsm.entity.userlogin.UserLogIn;
import com.dysonstudentmanagement.dsm.exception.InvalidUserTypeException;
import com.dysonstudentmanagement.dsm.repository.UserPrimaryDataRepository;
import com.dysonstudentmanagement.dsm.service.UserLogInService;
import com.dysonstudentmanagement.dsm.security.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class UserLogInController {
    private final UserLogInService userLogInService;

    private final JwtUtils jwtUtils;

    private UserPrimaryDataRepository userPrimaryDataRepository;

    public UserLogInController(UserLogInService userLogInService, JwtUtils jwtUtils
                               ) {
        this.userLogInService = userLogInService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<?> createUserLogin(@RequestBody UserLogInDto userLoginDto) {

        try {
            if(userLoginDto.getUserID() == null || userLoginDto.getPassword() == null) {
                throw new InvalidUserTypeException("UserID or Password is Empty");
            }
            UserLogIn userLogin = userLogInService.getUserByUserIDAndPassword(userLoginDto.getUserID(), userLoginDto.getPassword());
            if(userLogin == null){
                throw new InvalidUserTypeException("UserID or Password is Invalid");
            }
            String token = jwtUtils.generateJwt(userLoginDto.getUserID());
            return ResponseEntity.ok(token);
        } catch (InvalidUserTypeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }


}
