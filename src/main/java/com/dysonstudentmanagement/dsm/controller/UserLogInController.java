package com.dysonstudentmanagement.dsm.controller;

import com.dysonstudentmanagement.dsm.dto.AuthResponseDto;
import com.dysonstudentmanagement.dsm.dto.UserLogInDto;
import com.dysonstudentmanagement.dsm.dto.user.UserPrimaryDataDto;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.entity.userlogin.UserLogIn;
import com.dysonstudentmanagement.dsm.exception.InvalidUserTypeException;
import com.dysonstudentmanagement.dsm.repository.UserPrimaryDataRepository;
import com.dysonstudentmanagement.dsm.service.UserLogInService;
import com.dysonstudentmanagement.dsm.security.JwtUtils;
import com.dysonstudentmanagement.dsm.service.UserPrimaryDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class UserLogInController {
    private final UserLogInService userLogInService;

    private final UserPrimaryDataService userPrimaryDataService;

    private final JwtUtils jwtUtils;

    public UserLogInController(UserLogInService userLogInService, JwtUtils jwtUtils,
                               UserPrimaryDataService userPrimaryDataService
                               ) {
        this.userLogInService = userLogInService;
        this.jwtUtils = jwtUtils;
        this.userPrimaryDataService = userPrimaryDataService;
    }

    @CrossOrigin(origins = "http://localhost:3000/")
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
            UserPrimaryDataDto userPrimaryDataDto = userPrimaryDataService.getUserPrimaryDataById(userLoginDto.getUserID());

            AuthResponseDto authResponseDto = new AuthResponseDto();
            authResponseDto.setUserID(userPrimaryDataDto.getUserID());
            authResponseDto.setDysonEmail(userPrimaryDataDto.getDysonEmail());
            authResponseDto.setFirstName(userPrimaryDataDto.getFirstName());
            authResponseDto.setLastName(userPrimaryDataDto.getLastName());
            authResponseDto.setUserType(userPrimaryDataDto.getUserType());
            String token = jwtUtils.generateJwt(userLoginDto.getUserID());
            authResponseDto.setAccessToken(token);
            return ResponseEntity.ok(authResponseDto);
        } catch (InvalidUserTypeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }


}
