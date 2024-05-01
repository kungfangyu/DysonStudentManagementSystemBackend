package com.dysonstudentmanagement.dsm.entitylistener;

import com.dysonstudentmanagement.dsm.entity.studentexamgrade.StudentExamGrade;
import com.dysonstudentmanagement.dsm.entity.studentlessonallocation.StudentLessonAllocation;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.exception.InvalidUserTypeException;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class StudentLessonAllocationListener {
    @PrePersist
    @PreUpdate
    private void beforeCreatingOrUpdating(StudentLessonAllocation studentLessonAllocation){
        UserPrimaryData referencedStudent = studentLessonAllocation.getStudentPrimaryData();
        UserPrimaryData.UserType referencedStudentType = referencedStudent.getUserType();
        if(referencedStudentType != UserPrimaryData.UserType.student){
            throw new InvalidUserTypeException("Referenced StudentID does not point to a student");
        }
    }
}
