package com.dysonstudentmanagement.dsm.mapper;

import com.dysonstudentmanagement.dsm.dto.UserPrimaryDataDto;
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
                userPrimaryData.getPhone(),
                userPrimaryData.getIdPhoto(),
                userPrimaryData.getTitle(),
                userPrimaryData.getMiddleNames(),
                userPrimaryData.getGender(),
                userPrimaryData.getEthnicity(),
                userPrimaryData.getAddress(),
                userPrimaryData.getPostcode()
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
                .idPhoto(userPrimaryDataDto.getIdPhoto())
                .title(userPrimaryDataDto.getTitle())
                .middleNames(userPrimaryDataDto.getMiddleNames())
                .gender(userPrimaryDataDto.getGender())
                .ethnicity(userPrimaryDataDto.getEthnicity())
                .address(userPrimaryDataDto.getAddress())
                .postcode(userPrimaryDataDto.getPostcode())
                .build();
        return userPrimaryData;
}
}
