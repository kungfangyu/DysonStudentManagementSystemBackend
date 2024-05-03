package com.dysonstudentmanagement.dsm.entitylistener;

import com.dysonstudentmanagement.dsm.entity.studentcourseworkgrade.StudentCourseworkGrade;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.exception.InvalidUserTypeException;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

/*
StudentCourseworkGradeListener

Acts as a 'sql trigger' detecting attempts to change a table in the database that lists the class as an entity listener.
This entity listener is used by the StudentCourseworkGrade entity.

Original Author: Billy Peters 01/05/2024
 */
public class StudentCourseworkGradeListener {
    @PrePersist
    @PreUpdate
    /*
    beforeCreatingOrUpdating(StudentCourseworkGrade)
    Checks the referenced UserPrimaryData record to ensure that the referenced user is a student
    Original Author: Billy Peters 01/05/2024
     */
    private void beforeCreatingOrUpdating(StudentCourseworkGrade studentCourseworkGrade){
        UserPrimaryData referencedStudent = studentCourseworkGrade.getUserPrimaryData();
        UserPrimaryData.UserType referencedStudentType = referencedStudent.getUserType();
        if(referencedStudentType != UserPrimaryData.UserType.student){
            throw new InvalidUserTypeException("Referenced StudentID does not point to a student");
        }
    }
}
