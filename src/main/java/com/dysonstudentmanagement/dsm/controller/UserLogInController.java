package com.dysonstudentmanagement.dsm.controller;

import com.dysonstudentmanagement.dsm.dto.AuthResponseDto;
import com.dysonstudentmanagement.dsm.dto.UserLogInDto;
import com.dysonstudentmanagement.dsm.dto.UserPrimaryDataDto;
import com.dysonstudentmanagement.dsm.entity.userlogin.UserLogIn;
import com.dysonstudentmanagement.dsm.exception.InvalidUserTypeException;
import com.dysonstudentmanagement.dsm.service.UserLogInService;
import com.dysonstudentmanagement.dsm.security.JwtUtils;
import com.dysonstudentmanagement.dsm.service.UserPrimaryDataService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
/*
UserLogInController

Provides api mappings to access userLogInService methods. Also provides a method to authorise login requests, returning a JWT (JSON Web Token)

Original Author: Fang-Yu Kung 28/04/2024
Modifying Author: Billy Peters 30/04/2024: Added createUserLogin and updateUserPassword methods/mappings
 */
public class UserLogInController {
    private final UserLogInService userLogInService;

    private final UserPrimaryDataService userPrimaryDataService;

    private final JwtUtils jwtUtils;


    @PostMapping("/api/createPassword")
    public ResponseEntity<String> createUserLogin(@RequestBody UserLogInDto userLogInDto){
        String savedUser = userLogInService.createUserLogIn(userLogInDto);
        return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:3000/")
    @PostMapping("/api/login")

    public ResponseEntity<?> getUserLogin(@RequestBody UserLogInDto userLoginDto) {

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
    @PutMapping("/api/createPassword")
    public ResponseEntity<String> updateUserPassword(@RequestBody UserLogInDto updateDto){
        String updated = userLogInService.updateUserPassword(updateDto);
        return ResponseEntity.ok(updated);
    }


}
