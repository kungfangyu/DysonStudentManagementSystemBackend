package com.dysonstudentmanagement.dsm.mapper;

import com.dysonstudentmanagement.dsm.dto.StudentModuleGradeDto;
import com.dysonstudentmanagement.dsm.entity.studentmodulegrade.StudentModuleGrade;

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
