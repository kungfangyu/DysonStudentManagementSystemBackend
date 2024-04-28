package com.dysonstudentmanagement.dsm.repository;

import com.dysonstudentmanagement.dsm.entity.userlogin.UserLogIn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLogInRepository extends JpaRepository<UserLogIn, String> {

    UserLogIn findByUserIDAndPassword(String userID, String password);

    UserLogIn findByUserID(String userID);
}
