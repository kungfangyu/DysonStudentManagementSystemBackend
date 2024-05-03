package com.dysonstudentmanagement.dsm.entitylistener;

import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import jakarta.persistence.PrePersist;

/*
UserPrimaryDataListener

Acts as a 'sql trigger' detecting attempts to change a table in the database that lists the class as an entity listener.
This entity listener is used by the UserPrimaryData entity.

Original Author: Billy Peters 24/04/2024
 */
public class UserPrimaryDataListener {

    /*
    beforeCreating(UserPrimaryData)
    Before adding a new record to the table, the new record's dyson email is automatically assigned to = userID + "@dyson.com"
    Original Author: Billy Peters 24/04/2024
     */
    @PrePersist
    private void beforeCreating(UserPrimaryData userPrimaryData){
        userPrimaryData.setDysonEmail(userPrimaryData.getUserID()+"@dyson.com");
    }
}
