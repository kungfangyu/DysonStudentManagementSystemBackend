package com.dysonstudentmanagement.dsm.repository;

import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/*
UserPrimaryDataRepository

Interface provides methods that access/alter the UserPrimaryData table

Original Author: Billy Peters
 */

public interface UserPrimaryDataRepository extends JpaRepository<UserPrimaryData,String> {

    List<UserPrimaryData> findByUserType(UserPrimaryData.UserType userType);
    List<UserPrimaryData> findByUserTypeIsNot(UserPrimaryData.UserType userType);


}
