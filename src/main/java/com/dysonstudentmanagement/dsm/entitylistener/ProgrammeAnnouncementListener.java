package com.dysonstudentmanagement.dsm.entitylistener;

import com.dysonstudentmanagement.dsm.entity.programmeannouncement.ProgrammeAnnouncement;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.exception.InvalidUserTypeException;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class ProgrammeAnnouncementListener {

    @PrePersist
    @PreUpdate
    private void beforeCreatingOrUpdating(ProgrammeAnnouncement programmeAnnouncement){
        UserPrimaryData referencedStaff = programmeAnnouncement.getUserPrimaryData();
        UserPrimaryData.UserType referencedStaffType = referencedStaff.getUserType();
        if(referencedStaffType == UserPrimaryData.UserType.student){
            throw new InvalidUserTypeException("Referenced StaffID points to a student");
        }


    }
}
