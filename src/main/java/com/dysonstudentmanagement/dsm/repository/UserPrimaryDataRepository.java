package com.dysonstudentmanagement.dsm.repository;

import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserPrimaryDataRepository extends JpaRepository<UserPrimaryData,String> {

    List<UserPrimaryData> findByUserType(UserPrimaryData.UserType userType);
    List<UserPrimaryData> findByUserTypeIsNot(UserPrimaryData.UserType userType);
}
