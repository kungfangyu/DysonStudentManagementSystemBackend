package com.dysonstudentmanagement.dsm.mapper;

import com.dysonstudentmanagement.dsm.dto.user.UserPrimaryDataDto;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;

public class UserPrimaryDataMapper {

    public static UserPrimaryDataDto mapToUserPrimaryDataDto(UserPrimaryData userPrimaryData){
        return new UserPrimaryDataDto(
                userPrimaryData.getUserID(),
                userPrimaryData.getUserType(),
                userPrimaryData.getFirstName(),
                userPrimaryData.getLastName(),
                userPrimaryData.getDateOfBirth(),
                userPrimaryData.getPersonalEmail(),
                userPrimaryData.getDysonEmail(),
                userPrimaryData.getPhone()
        );
    }

    public static UserPrimaryData mapToUserPrimaryData(UserPrimaryDataDto userPrimaryDataDto){
        UserPrimaryData userPrimaryData = UserPrimaryData.builder()
                .userID(userPrimaryDataDto.getUserID())
                .userType(userPrimaryDataDto.getUserType())
                .firstName(userPrimaryDataDto.getFirstName())
                .lastName(userPrimaryDataDto.getLastName())
                .dateOfBirth(userPrimaryDataDto.getDateOfBirth())
                .personalEmail(userPrimaryDataDto.getPersonalEmail())
                .dysonEmail(userPrimaryDataDto.getDysonEmail())
                .phone(userPrimaryDataDto.getPhone())
                .build();
        return userPrimaryData;
}
}
