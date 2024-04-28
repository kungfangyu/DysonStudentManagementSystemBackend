package com.dysonstudentmanagement.dsm.mapper;

import com.dysonstudentmanagement.dsm.dto.ExamDto;
import com.dysonstudentmanagement.dsm.entity.exam.Exam;

/**
 * Mapper class responsible for mapping between Exam and ExamDto objects.
 */
public class ExamMapper {

    /**
     * Maps an Exam entity to an ExamDto DTO.
     */
    public static ExamDto mapToExamDto(Exam exam) {
        return new ExamDto(
                exam.getExamID(),             // Mapping exam ID
                exam.getModuleID(),           // Mapping module ID
                exam.getStartTime(),          // Mapping start time
                exam.getEndTime(),            // Mapping end time
                exam.getPercentageOfModule()  // Mapping percentage of module
        );
    }

    /**
     * Maps an ExamDto DTO to an Exam entity.
     */
    public static Exam mapToExam(ExamDto examDto) {
        // Building an Exam entity using the ExamDto fields
        Exam exam = Exam.builder()
                .examID(examDto.getExamID())                    // Mapping exam ID
                .moduleID(examDto.getModuleID())                // Mapping module ID
                .startTime(examDto.getStartTime())              // Mapping start time
                .endTime(examDto.getEndTime())                  // Mapping end time
                .percentageOfModule(examDto.getPercentageOfModule())  // Mapping percentage of module
                .build();

        return exam;
    }
}
