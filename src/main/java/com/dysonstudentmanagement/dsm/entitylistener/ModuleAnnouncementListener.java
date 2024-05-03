package com.dysonstudentmanagement.dsm.entitylistener;

import com.dysonstudentmanagement.dsm.entity.moduleannouncement.ModuleAnnouncement;
import com.dysonstudentmanagement.dsm.entity.studenttutor.StudentTutor;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.exception.InvalidUserTypeException;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

/*
ModuleAnnouncementListener

Acts as a 'sql trigger' detecting attempts to change a table in the database that lists the class as an entity listener.
This entity listener is used by the ModuleAnnouncement entity.

Original Author: Billy Peters 01/05/2024
 */
public class ModuleAnnouncementListener {

    @PrePersist
    @PreUpdate
    /*
    beforeCreatingOrUpdating(ModuleAnnouncement)
    Checks the referenced UserPrimaryData record to ensure that the referenced user is not a student
    Original Author: Billy Peters 01/05/2024
     */
    private void beforeCreatingOrUpdating(ModuleAnnouncement announcement){
        UserPrimaryData referencedStaff = announcement.getUserPrimaryData();
        UserPrimaryData.UserType referencedStaffType = referencedStaff.getUserType();
        if(referencedStaffType == UserPrimaryData.UserType.student){
            throw new InvalidUserTypeException("Referenced StaffID points to a student");
        }
    }
}
