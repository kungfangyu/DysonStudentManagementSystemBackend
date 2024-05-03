package com.dysonstudentmanagement.dsm.entitylistener;

import com.dysonstudentmanagement.dsm.entity.studentcourseworkgrade.StudentCourseworkGrade;
import com.dysonstudentmanagement.dsm.entity.studentexamgrade.StudentExamGrade;
import com.dysonstudentmanagement.dsm.entity.studenttutor.StudentTutor;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.exception.InvalidUserTypeException;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

/*
StudentExamGradeListener

Acts as a 'sql trigger' detecting attempts to change a table in the database that lists the class as an entity listener.
This entity listener is used by the StudentExamGrade entity.

Original Author: Billy Peters 01/05/2024
 */
public class StudentExamGradeListener {
    @PrePersist
    @PreUpdate
    /*
    beforeCreatingOrUpdating(StudentExamGrade)
    Checks the referenced UserPrimaryData record to ensure that the referenced user is a student
    Original Author: Billy Peters 01/05/2024
     */
    private void beforeCreatingOrUpdating(StudentExamGrade studentExamGrade){
        UserPrimaryData referencedStudent = studentExamGrade.getUserPrimaryData();
        UserPrimaryData.UserType referencedStudentType = referencedStudent.getUserType();
        if(referencedStudentType != UserPrimaryData.UserType.student){
            throw new InvalidUserTypeException("Referenced StudentID does not point to a student");
        }
    }
}
