package com.dysonstudentmanagement.dsm.service;

import com.dysonstudentmanagement.dsm.dto.StudentProgrammeEnrolmentDto;

import com.dysonstudentmanagement.dsm.entity.studentprogrammeenrolment.StudentProgrammeEnrolmentCompositeKey;

import java.util.List;

/*
StudentProgrammeEnrolmentService

Interface that declares service methods that act upon StudentProgrammeEnrolment table in database

Original Author: Imran Matloob 24/04/2024
 */
public interface StudentProgrammeEnrolmentService {

    StudentProgrammeEnrolmentDto createStudentProgrammeEnrolment(StudentProgrammeEnrolmentDto studentProgrammeEnrolmentDto);

    List<StudentProgrammeEnrolmentDto> getAllStudentProgrammeEnrolment();

    StudentProgrammeEnrolmentDto getStudentProgrammeEnrolmentDto(StudentProgrammeEnrolmentCompositeKey targetKey);

    List<StudentProgrammeEnrolmentDto> getStudentProgrammeEnrolmentDtoByStudentID(String studentID);

    List<StudentProgrammeEnrolmentDto> getStudentProgrammeEnrolmentDtoByProgrammeID(String programmeID);

    StudentProgrammeEnrolmentDto updateStudentProgrammeEnrolment(StudentProgrammeEnrolmentCompositeKey targetKey,
                                                                 StudentProgrammeEnrolmentDto studentProgrammeEnrolmentDto);

    void deleteStudentProgrammeEnrolment(StudentProgrammeEnrolmentCompositeKey targetKey);
}
