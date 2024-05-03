package com.dysonstudentmanagement.dsm.entitylistener;

import com.dysonstudentmanagement.dsm.entity.studentmodulegrade.StudentModuleGrade;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.exception.InvalidUserTypeException;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

/*
StudentModuleGradeListener

Acts as a 'sql trigger' detecting attempts to change a table in the database that lists the class as an entity listener.
This entity listener is used by the StudentModuleGrade entity.

Original Author: Billy Peters 01/05/2024
 */
public class StudentModuleGradeListener {
    @PrePersist
    @PreUpdate
    /*
    beforeCreatingOrUpdating(StudentModuleGrade)
    Checks the referenced UserPrimaryData record to ensure that the referenced user is a student
    Original Author: Billy Peters 01/05/2024
     */
    private void beforeCreatingOrUpdating(StudentModuleGrade studentModuleGrade){
        UserPrimaryData referencedStudent = studentModuleGrade.getUserPrimaryData();
        UserPrimaryData.UserType referencedStudentType = referencedStudent.getUserType();
        if(referencedStudentType != UserPrimaryData.UserType.student){
            throw new InvalidUserTypeException("Referenced StudentID does not point to a student");
        }

    }
}
