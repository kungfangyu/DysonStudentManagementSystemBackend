package com.dysonstudentmanagement.dsm.service;

import com.dysonstudentmanagement.dsm.dto.StudentProgrammeEnrolmentDto;
import com.dysonstudentmanagement.dsm.entity.StudentProgrammeEnrolment.StudentProgrammeEnrolmentCompositeKey;

import java.util.List;

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
