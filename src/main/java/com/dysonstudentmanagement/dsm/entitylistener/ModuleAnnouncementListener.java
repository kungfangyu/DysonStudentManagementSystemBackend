package com.dysonstudentmanagement.dsm.entitylistener;

import com.dysonstudentmanagement.dsm.entity.moduleannouncement.ModuleAnnouncement;
import com.dysonstudentmanagement.dsm.entity.studenttutor.StudentTutor;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.exception.InvalidUserTypeException;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class ModuleAnnouncementListener {

    @PrePersist
    @PreUpdate
    private void beforeCreatingOrUpdating(ModuleAnnouncement announcement){
        UserPrimaryData referencedStaff = announcement.getUserPrimaryData();
        UserPrimaryData.UserType referencedStaffType = referencedStaff.getUserType();
        if(referencedStaffType == UserPrimaryData.UserType.student){
            throw new InvalidUserTypeException("Referenced StaffID points to a student");
        }
    }
}
