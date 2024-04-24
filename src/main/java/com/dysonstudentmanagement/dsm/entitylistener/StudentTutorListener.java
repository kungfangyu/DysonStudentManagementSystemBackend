package com.dysonstudentmanagement.dsm.entitylistener;

import com.dysonstudentmanagement.dsm.entity.studenttutor.StudentTutor;
import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;
import com.dysonstudentmanagement.dsm.exception.InvalidUserTypeException;
import com.dysonstudentmanagement.dsm.repository.StudentTutorRepository;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentTutorListener {
    @PrePersist
    @PreUpdate
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
