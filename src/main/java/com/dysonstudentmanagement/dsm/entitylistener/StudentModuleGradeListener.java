package com.dysonstudentmanagement.dsm.entitylistener;

import com.dysonstudentmanagement.dsm.entity.studentmodulegrade.StudentModuleGrade;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.exception.InvalidUserTypeException;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class StudentModuleGradeListener {
    @PrePersist
    @PreUpdate
    private void beforeCreatingOrUpdating(StudentModuleGrade studentModuleGrade){
        UserPrimaryData referencedStudent = studentModuleGrade.getUserPrimaryData();
        UserPrimaryData.UserType referencedStudentType = referencedStudent.getUserType();
        if(referencedStudentType != UserPrimaryData.UserType.student){
            throw new InvalidUserTypeException("Referenced StudentID does not point to a student");
        }

    }
}
