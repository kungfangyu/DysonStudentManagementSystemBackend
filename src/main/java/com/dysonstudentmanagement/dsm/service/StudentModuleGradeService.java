package com.dysonstudentmanagement.dsm.service;

import com.dysonstudentmanagement.dsm.dto.ModuleDetailsDto;
import com.dysonstudentmanagement.dsm.dto.StudentModuleGradeDto;
import com.dysonstudentmanagement.dsm.entity.studentmodulegrade.StudentModuleGradeCompositeKey;

import java.util.List;

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
