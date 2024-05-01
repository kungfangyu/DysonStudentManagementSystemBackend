package com.dysonstudentmanagement.dsm.service;

import com.dysonstudentmanagement.dsm.dto.LessonDto;
import com.dysonstudentmanagement.dsm.dto.StudentLessonAllocationDto;
import com.dysonstudentmanagement.dsm.entity.studentlessonallocation.StudentLessonAllocationCompositeKey;

import java.util.List;

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
