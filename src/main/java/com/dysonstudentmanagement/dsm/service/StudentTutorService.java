package com.dysonstudentmanagement.dsm.service;

import com.dysonstudentmanagement.dsm.dto.StudentTutorDto;
import com.dysonstudentmanagement.dsm.entity.studenttutor.StudentTutorCompositeKey;

import java.util.List;

/*
StudentTutorService

Interface that declares service methods that act upon StudentTutor table in database

Original Author: Billy Peters 24/04/2024
 */
public interface StudentTutorService {
    StudentTutorDto createStudentTutor(StudentTutorDto studentTutorDto);

    StudentTutorDto createStudentTutorRandomAssign(String studentID);

    StudentTutorDto getStudentTutor(StudentTutorCompositeKey targetKey);

    List<StudentTutorDto> getStudentTutorByStaffID(String staffID);

    List<StudentTutorDto> getStudentTutorByStudentID(String studentID); // On creation of method (24/04/2024) a student should only have one tutor, but this method provides a list in case requirements are changed in the future.

    StudentTutorDto updateStudentTutor(StudentTutorCompositeKey targetKey, StudentTutorDto updatedStudentTutorDto);

    void deleteStudentTutor(StudentTutorCompositeKey targetKey);
}
