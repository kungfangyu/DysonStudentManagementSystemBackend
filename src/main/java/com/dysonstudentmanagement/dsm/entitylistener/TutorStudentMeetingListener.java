package com.dysonstudentmanagement.dsm.entitylistener;

import com.dysonstudentmanagement.dsm.entity.studenttutor.StudentTutor;
import com.dysonstudentmanagement.dsm.entity.tutorstudentmeeting.TutorStudentMeeting;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.exception.InvalidUserTypeException;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

/*
TutorStudentMeetingListener

Acts as a 'sql trigger' detecting attempts to change a table in the database that lists the class as an entity listener.
This entity listener is used by the TutorStudentMeeting entity.

Original Author: Billy Peters 01/05/2024
 */
public class TutorStudentMeetingListener {
    @PrePersist
    @PreUpdate
    /*
    beforeCreatingOrUpdating(TutorStudentMeeting)
    Checks the referenced 'student' UserPrimaryData record to ensure that the referenced user is a student, and
    checks the referenced 'staff' UserPrimaryData record to ensure that the referenced user is not a student
    Original Author: Billy Peters 01/05/2024
     */
    private void beforeCreatingOrUpdating(TutorStudentMeeting tutorStudentMeeting) {
        UserPrimaryData referencedStudent = tutorStudentMeeting.getStudentPrimaryData();
        UserPrimaryData referencedStaff = tutorStudentMeeting.getStaffPrimaryData();
        UserPrimaryData.UserType referencedStudentType = referencedStudent.getUserType();
        UserPrimaryData.UserType referencedStaffType = referencedStaff.getUserType();
        if (referencedStaffType == UserPrimaryData.UserType.student) {
            throw new InvalidUserTypeException("Referenced StaffID points to a student");
        }
        if (referencedStudentType != UserPrimaryData.UserType.student && referencedStudentType != null) {
            throw new InvalidUserTypeException("Referenced StudentID does not point to a student");
        }

    }
}
