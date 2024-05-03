package com.dysonstudentmanagement.dsm.service;

import com.dysonstudentmanagement.dsm.dto.StudentExamGradeDto;
import com.dysonstudentmanagement.dsm.entity.studentexamgrade.StudentExamGradeCompositeKey;

import java.util.List;


/*
StudentExamGradeService

Service interface for managing student exam grades.

Original Author: Yijie Zhu 27/04/2024
Modifying Author: Imran Matloob 28/04/2024 Implemented composite keys
 */
public interface StudentExamGradeService {

    StudentExamGradeDto createStudentExamGrade( StudentExamGradeDto studentExamGradeDto);
    StudentExamGradeDto getStudentExamGrade(StudentExamGradeCompositeKey targetKey);
    List<StudentExamGradeDto> getStudentExamGradeByModuleIDAndExamID(String moduleID,int examID);
    StudentExamGradeDto updateStudentExamGrade(StudentExamGradeCompositeKey studentExamGradeCompositeKey, StudentExamGradeDto studentExamGradeDto);
    void deleteStudentExamGrade(StudentExamGradeCompositeKey studentExamGradeID);

}
