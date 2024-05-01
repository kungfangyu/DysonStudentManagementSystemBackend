package com.dysonstudentmanagement.dsm.entitylistener;

import com.dysonstudentmanagement.dsm.entity.studentcourseworkgrade.StudentCourseworkGrade;
import com.dysonstudentmanagement.dsm.entity.studentexamgrade.StudentExamGrade;
import com.dysonstudentmanagement.dsm.entity.studenttutor.StudentTutor;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.exception.InvalidUserTypeException;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class StudentExamGradeListener {
    @PrePersist
    @PreUpdate
    private void beforeCreatingOrUpdating(StudentExamGrade studentExamGrade){
        UserPrimaryData referencedStudent = studentExamGrade.getUserPrimaryData();
        UserPrimaryData.UserType referencedStudentType = referencedStudent.getUserType();
        if(referencedStudentType != UserPrimaryData.UserType.student){
            throw new InvalidUserTypeException("Referenced StudentID does not point to a student");
        }
    }
}
