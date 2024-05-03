package com.dysonstudentmanagement.dsm.service;

import com.dysonstudentmanagement.dsm.dto.ModuleDetailsDto;
import com.dysonstudentmanagement.dsm.dto.StudentModuleGradeDto;
import com.dysonstudentmanagement.dsm.entity.studentmodulegrade.StudentModuleGradeCompositeKey;

import java.util.List;

/*
StudentModuleGradeService

Interface that declares service methods that act upon StudentModuleGrade table in database

Original Author: Grace Liu 25/04/2024
 */
public interface StudentModuleGradeService {
    StudentModuleGradeDto createStudentModuleGrade(StudentModuleGradeDto studentModuleGradeDto);
    StudentModuleGradeDto getStudentModuleGrade(StudentModuleGradeCompositeKey studentModuleGradeCompositeKey);
    List<StudentModuleGradeDto> getStudentModuleGradeByStudentID(String studentID);
    List<StudentModuleGradeDto> getStudentModuleGradeByModuleID(String moduleID);

    List<ModuleDetailsDto> getModuleDetailsByStudentID(String studentID);
    StudentModuleGradeDto updateStudentModuleGrade(StudentModuleGradeCompositeKey studentModuleGradeCompositeKey,
                                                   StudentModuleGradeDto studentModuleGradeDto);
    void deleteStudentModuleGrade(StudentModuleGradeCompositeKey studentModuleGradeCompositeKey);
}
