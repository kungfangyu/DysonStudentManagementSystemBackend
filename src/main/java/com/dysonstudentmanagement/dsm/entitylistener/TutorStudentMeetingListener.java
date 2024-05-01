package com.dysonstudentmanagement.dsm.entitylistener;

import com.dysonstudentmanagement.dsm.entity.studenttutor.StudentTutor;
import com.dysonstudentmanagement.dsm.entity.tutorstudentmeeting.TutorStudentMeeting;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.exception.InvalidUserTypeException;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class TutorStudentMeetingListener {
    @PrePersist
    @PreUpdate
    private void beforeCreatingOrUpdating(TutorStudentMeeting tutorStudentMeeting) {
        UserPrimaryData referencedStudent = tutorStudentMeeting.getStudentPrimaryData();
        UserPrimaryData referencedStaff = tutorStudentMeeting.getStaffPrimaryData();
        UserPrimaryData.UserType referencedStudentType = referencedStudent.getUserType();
        UserPrimaryData.UserType referencedStaffType = referencedStaff.getUserType();
        if (referencedStaffType == UserPrimaryData.UserType.student) {
            throw new InvalidUserTypeException("Referenced StaffID points to a student");
        }
        if (referencedStudentType != UserPrimaryData.UserType.student) {
            throw new InvalidUserTypeException("Referenced StudentID does not point to a student");
        }

    }
}
