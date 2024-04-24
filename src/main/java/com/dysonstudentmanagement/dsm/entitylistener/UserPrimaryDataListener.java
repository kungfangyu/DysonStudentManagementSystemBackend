package com.dysonstudentmanagement.dsm.entitylistener;

import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import jakarta.persistence.PrePersist;

public class UserPrimaryDataListener {

    @PrePersist
    private void beforeCreating(UserPrimaryData userPrimaryData){
        userPrimaryData.setDysonEmail(userPrimaryData.getUserID()+"@dyson.com");
    }
}
