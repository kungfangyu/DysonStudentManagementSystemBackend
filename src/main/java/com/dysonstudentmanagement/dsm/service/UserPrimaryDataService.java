package com.dysonstudentmanagement.dsm.service;

import com.dysonstudentmanagement.dsm.dto.UserPrimaryDataDto;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;

import java.util.Collection;
import java.util.List;

/*
UserPrimaryDataService

Interface that declares service methods that act upon UserPrimaryData table in database

Original Author: Billy Peters 23/04/2024
 */
public interface UserPrimaryDataService {
    UserPrimaryDataDto createUserPrimaryData(UserPrimaryDataDto userPrimaryDataDto);

    UserPrimaryDataDto getUserPrimaryDataById(String userID);

    List<UserPrimaryDataDto> getAllUserPrimaryData();

    List<UserPrimaryDataDto> getCollectionUserPrimaryDataByIDs(Collection<String> userIDs);

    List<UserPrimaryDataDto> getAllUserPrimaryDataByUserType(UserPrimaryData.UserType userType);

    UserPrimaryDataDto updateUserPrimaryData(String userID, UserPrimaryDataDto updatedPrimaryData);

    void deleteEmployee(String userID);

}
