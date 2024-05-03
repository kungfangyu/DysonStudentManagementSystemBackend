package com.dysonstudentmanagement.dsm.service;

import com.dysonstudentmanagement.dsm.dto.LessonDto;
import com.dysonstudentmanagement.dsm.dto.StudentLessonAllocationDto;
import com.dysonstudentmanagement.dsm.entity.studentlessonallocation.StudentLessonAllocationCompositeKey;

import java.util.List;

/*
StudentLessonAllocationService

Interface that declares service methods that act upon StudentLessonAllocation table in database

Original Author: Billy Peters 26/04/2024
 */
public interface StudentLessonAllocationService {
    StudentLessonAllocationDto createStudentLessonAllocation(StudentLessonAllocationDto studentLessonAllocationDto);

    List<StudentLessonAllocationDto> getStudentLessonAllocationByStudentID(String studentID);
    List<StudentLessonAllocationDto> getStudentLessonAllocationByModuleID(String moduleID);
    List<StudentLessonAllocationDto> getStudentLessonAllocationByModuleIDAndStudentID(String moduleID, String studentID);

    List<StudentLessonAllocationDto> getStudentLessonAllocationByModuleIDAndLessonID(String moduleID, int lessonID);

    List<LessonDto> getLessonInformationByStudentID(String studentID);

    StudentLessonAllocationDto updateStudentLessonAllocation(StudentLessonAllocationCompositeKey targetKey, StudentLessonAllocationDto updatedDto);
    void deleteStudentLessonAllocation(StudentLessonAllocationCompositeKey targetKey);
}
