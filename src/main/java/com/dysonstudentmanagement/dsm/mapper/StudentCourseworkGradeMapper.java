package com.dysonstudentmanagement.dsm.mapper;

import com.dysonstudentmanagement.dsm.dto.StudentCourseworkGradeDto;
import com.dysonstudentmanagement.dsm.entity.studentcourseworkgrade.StudentCourseworkGrade;

public class StudentCourseworkGradeMapper {
    public static StudentCourseworkGradeDto mapToStudentCourseworkGradeDto (StudentCourseworkGrade studentCourseworkGrade) {
        return new StudentCourseworkGradeDto(
                studentCourseworkGrade.getModuleID(),
                studentCourseworkGrade.getCourseworkID(),
                studentCourseworkGrade.getStudentID(),
                studentCourseworkGrade.getSubmissionFilePath(),
                studentCourseworkGrade.getSubmissionTime(),
                studentCourseworkGrade.isOnTime(),
                studentCourseworkGrade.getGrade(),
                studentCourseworkGrade.getFeedback()
        );
    }

    public static StudentCourseworkGrade mapToStudentCourseworkGrade (StudentCourseworkGradeDto studentCourseworkGradeDto) {
        return StudentCourseworkGrade.builder()
                .moduleID(studentCourseworkGradeDto.getModuleID())
                .courseworkID(studentCourseworkGradeDto.getCourseworkID())
                .studentID(studentCourseworkGradeDto.getStudentID())
                .submissionFilePath(studentCourseworkGradeDto.getSubmissionFilePath())
                .submissionTime(studentCourseworkGradeDto.getSubmissionTime())
                .isOnTime(studentCourseworkGradeDto.isOnTime())
                .grade(studentCourseworkGradeDto.getGrade())
                .feedback(studentCourseworkGradeDto.getFeedback())
                .build();

    }
}
