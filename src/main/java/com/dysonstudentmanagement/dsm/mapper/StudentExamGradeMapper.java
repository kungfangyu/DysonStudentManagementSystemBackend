package com.dysonstudentmanagement.dsm.mapper;

import com.dysonstudentmanagement.dsm.dto.StudentExamGradeDto;
import com.dysonstudentmanagement.dsm.entity.studentexamgrade.StudentExamGrade;

/*
    Class: StudentExamGradeMapper
    Mapper class responsible for mapping between StudentExamGrade and StudentExamGradeDto objects.
    Original Author: Yijie Zhu 27/04/2024
 */
public class StudentExamGradeMapper {

    /**
     * Maps a StudentExamGrade entity to a StudentExamGradeDto DTO.
     * @return The mapped StudentExamGradeDto DTO
     */
    public static StudentExamGradeDto mapToStudentExamGradeDto(StudentExamGrade studentExamGrade) {
        return new StudentExamGradeDto(
                studentExamGrade.getModuleID(),      // Mapping module ID
                studentExamGrade.getExamID(),       // Mapping exam ID
                studentExamGrade.getStudentID(),        // Mapping user ID
                studentExamGrade.getGrade()          // Mapping grade
        );
    }

    /**
     * Maps a StudentExamGradeDto DTO to a StudentExamGrade entity.
     */
    public static StudentExamGrade mapToStudentExamGrade(StudentExamGradeDto studentExamGradeDto) {
        // Building a StudentExamGrade entity using the StudentExamGradeDto fields
        StudentExamGrade studentExamGrade = StudentExamGrade.builder()
                .moduleID(studentExamGradeDto.getModuleID())  // Mapping module ID
                .examID(studentExamGradeDto.getExamID())    // Mapping exam ID
                .studentID(studentExamGradeDto.getStudentID())    // Mapping user ID
                .grade(studentExamGradeDto.getGrade())      // Mapping grade
                .build();

        return studentExamGrade;
    }
}
