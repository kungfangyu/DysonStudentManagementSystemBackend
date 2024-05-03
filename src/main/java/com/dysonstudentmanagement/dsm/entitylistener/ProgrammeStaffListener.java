package com.dysonstudentmanagement.dsm.entitylistener;

import com.dysonstudentmanagement.dsm.entity.programmestaff.ProgrammeStaff;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.exception.InvalidUserTypeException;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

/*
ProgrammeStaffListener

Acts as a 'sql trigger' detecting attempts to change a table in the database that lists the class as an entity listener.
This entity listener is used by the ProgrammeStaff entity.

Original Author: Billy Peters 01/05/2024
 */
public class ProgrammeStaffListener {

    @PrePersist
    @PreUpdate
    /*
    beforeCreatingOrUpdating(ProgrammeStaff)
    Checks the referenced UserPrimaryData record to ensure that the referenced user is not a student
    Original Author: Billy Peters 01/05/2024
     */
    private void beforeCreatingOrUpdating(ProgrammeStaff programmeStaff){
        UserPrimaryData referencedStaff = programmeStaff.getStaffPrimaryData();
        UserPrimaryData.UserType referencedStaffType = referencedStaff.getUserType();
        if(referencedStaffType == UserPrimaryData.UserType.student){
            throw new InvalidUserTypeException("Referenced StaffID points to a student");
        }
    }
}
