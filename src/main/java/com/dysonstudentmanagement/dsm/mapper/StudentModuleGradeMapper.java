package com.dysonstudentmanagement.dsm.mapper;

import com.dysonstudentmanagement.dsm.dto.StudentModuleGradeDto;
import com.dysonstudentmanagement.dsm.entity.studentmodulegrade.StudentModuleGrade;

/*
    Class: StudentModuleGradeMapper
    Mapper class responsible for mapping between StudentModuleGrade and StudentModuleGradeDto objects.
    Original Author: Grace Liu 25/04/2024
 */
public class StudentModuleGradeMapper {
    public static StudentModuleGradeDto mapToStudentModuleGradeDto(StudentModuleGrade studentModuleGrade) {
        return new StudentModuleGradeDto(
                studentModuleGrade.getStudentID(),
                studentModuleGrade.getModuleID(),
                studentModuleGrade.getGrade(),
                studentModuleGrade.getPercentageAttendance()
        );
    }

    public static StudentModuleGrade mapToStudentModuleGrade(StudentModuleGradeDto studentModuleGradeDto) {
        return StudentModuleGrade.builder()
                .studentID(studentModuleGradeDto.getStudentID())
                .moduleID(studentModuleGradeDto.getModuleID())
                .grade(studentModuleGradeDto.getGrade())
                .percentageAttendance(studentModuleGradeDto.getPercentageAttendance())
                .build();
    }
}
