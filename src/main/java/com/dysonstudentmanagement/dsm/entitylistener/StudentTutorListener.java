package com.dysonstudentmanagement.dsm.entitylistener;

import com.dysonstudentmanagement.dsm.entity.studenttutor.StudentTutor;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.exception.InvalidUserTypeException;
import com.dysonstudentmanagement.dsm.repository.StudentTutorRepository;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;

/*
StudentTutorListener

Acts as a 'sql trigger' detecting attempts to change a table in the database that lists the class as an entity listener.
This entity listener is used by the StudentTutor entity.

Original Author: Billy Peters 01/05/2024
 */
public class StudentTutorListener {
    @PrePersist
    @PreUpdate
    /*
    beforeCreatingOrUpdating(StudentTutor)
    Checks the referenced 'student' UserPrimaryData record to ensure that the referenced user is a student, and
    checks the referenced 'staff' UserPrimaryData record to ensure that the referenced user is not a student
    Original Author: Billy Peters 01/05/2024
     */
    private void beforeCreatingOrUpdating(StudentTutor studentTutor){
        UserPrimaryData referencedStudent = studentTutor.getStudentPrimaryData();
        UserPrimaryData referencedStaff = studentTutor.getStaffPrimaryData();
        UserPrimaryData.UserType referencedStudentType = referencedStudent.getUserType();
        UserPrimaryData.UserType referencedStaffType = referencedStaff.getUserType();
        if(referencedStaffType == UserPrimaryData.UserType.student){
            throw new InvalidUserTypeException("Referenced StaffID points to a student");
        }
        if(referencedStudentType != UserPrimaryData.UserType.student){
            throw new InvalidUserTypeException("Referenced StudentID does not point to a student");
        }



    }
}
