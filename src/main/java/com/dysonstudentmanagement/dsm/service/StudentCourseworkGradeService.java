package com.dysonstudentmanagement.dsm.service;

import com.dysonstudentmanagement.dsm.dto.StudentCourseworkGradeDto;
import com.dysonstudentmanagement.dsm.entity.studentcourseworkgrade.StudentCourseworkGradeCompositeKey;

import java.util.List;

/*
StudentCourseworkGradeService

Interface that declares service methods that act upon StudentCourseworkGrade table in database

Original Author: Jack Burnett 27/04/2024
 */
public interface StudentCourseworkGradeService {
    StudentCourseworkGradeDto createStudentCourseworkGrade(StudentCourseworkGradeDto studentCourseworkGradeDto);

    StudentCourseworkGradeDto getStudentCourseworkGrade(StudentCourseworkGradeCompositeKey targetKey);

    List<StudentCourseworkGradeDto> getStudentCourseGradeByModuleIdAndCourseworkID(String moduleID, int courseworkID);

    StudentCourseworkGradeDto updateStudentCourseworkGrade(StudentCourseworkGradeCompositeKey targetKey, StudentCourseworkGradeDto studentCourseworkGradeDto);

    void deleteStudentCourseworkGrade(StudentCourseworkGradeCompositeKey targetKey);
}
